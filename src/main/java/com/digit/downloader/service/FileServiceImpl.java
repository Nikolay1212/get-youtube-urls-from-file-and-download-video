package com.digit.downloader.service;

import com.digit.downloader.dto.UrlDto;
import com.digit.downloader.model.FileInfo;
import com.digit.downloader.model.Url;
import com.digit.downloader.parser.ParserUrl;
import com.digit.downloader.repository.FileInfoRepository;
import com.digit.downloader.repository.UrlRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileInfoRepository fileInfoRepository;

    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private ParserUrl parser;

    @Override
    public Long save(MultipartFile file) throws IOException {
        Path tempPath = Paths.get("temp" + ".xlsx");
        List<String> urls;
        String path;
        try (OutputStream os = Files.newOutputStream(tempPath)) {
            os.write(file.getBytes());
            path = tempPath.toAbsolutePath().toString();
            urls = parser.parse(path);

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        String storageName = UUID.randomUUID() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        FileInfo fileInfo = FileInfo.builder()
                .ordinalName(file.getOriginalFilename())
                .storageFileName(storageName)
                .path(path)
                .size(file.getSize())
                .type(file.getContentType())
                .build();
        Long id = fileInfoRepository.save(fileInfo).getId();
        for (String str : urls) {
            Url url = Url.builder()
                    .url(str)
                    .fileInfo(fileInfo)
                    .build();
            urlRepository.save(url);
        }
        return id;
    }

    @Override
    public List<UrlDto> getAllUrlFromFile(Long fileId) {
        List<UrlDto> urls = UrlDto.from(urlRepository.findAllByFileInfoId(fileId));
        System.out.println(urls.size() + " urls count");
        return urls;
    }
}
