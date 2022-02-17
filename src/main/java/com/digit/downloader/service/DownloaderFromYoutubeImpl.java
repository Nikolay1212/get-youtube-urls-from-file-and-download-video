package com.digit.downloader.service;

import com.digit.downloader.model.Url;
import com.digit.downloader.repository.UrlRepository;
import io.github.gaeqs.javayoutubedownloader.JavaYoutubeDownloader;
import io.github.gaeqs.javayoutubedownloader.decoder.MultipleDecoderMethod;
import io.github.gaeqs.javayoutubedownloader.stream.StreamOption;
import io.github.gaeqs.javayoutubedownloader.stream.YoutubeVideo;
import io.github.gaeqs.javayoutubedownloader.stream.download.StreamDownloader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class DownloaderFromYoutubeImpl implements Downloader {

    @Autowired
    private UrlRepository urlRepository;

    @Override
    public boolean downloadOneVideoFromFile(Long urlId) {
        File folder = new File("/Users/Studio/Desktop/Stepik/YD");
        String url = urlRepository.getById(urlId).getUrl();
        //Extracts and decodes all streams.
        YoutubeVideo video = JavaYoutubeDownloader.decodeOrNull(url, MultipleDecoderMethod.AND, "html", "embedded");
        //Gets the option with the greatest quality that has video and audio.
        StreamOption option = video.getStreamOptions().stream()
                .filter(target -> target.getType().hasVideo() && target.getType().hasAudio())
                .min(Comparator.comparingInt(o -> o.getType().getVideoQuality().ordinal())).orElse(null);
        //If there is no option, returns false.
        if (option == null) {
            return false;
        }
        //Prints the option type.
        //Creates the file. folder/title.extension
        File file = new File(folder, video.getTitle() + "." + option.getType().getContainer().toString().toLowerCase());
        //Creates the downloader.
        StreamDownloader downloader = new StreamDownloader(option, file, null);
        //Runs the downloader.
        new Thread(downloader).start();
        return true;
    }

    @Override
    public boolean downloadAllVideoFromFile(Long fileId) {
        List<Url> urlList = urlRepository.findAllByFileInfoId(fileId);
        for (Url url :
                urlList) {
            downloadOneVideoFromFile(url.getId());
        }
        return true;
    }
}
