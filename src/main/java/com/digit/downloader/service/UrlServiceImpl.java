package com.digit.downloader.service;

import com.digit.downloader.model.Url;
import com.digit.downloader.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UrlServiceImpl implements UrlService {

    @Autowired
    private UrlRepository urlRepository;

    @Override
    public void save(List<Url> urls) {
        urlRepository.saveAll(urls);
    }

    @Override
    public List<Url> getAllUrls() {
        return urlRepository.findAll();
    }

    @Override
    public List<Url> getUrlsFromFile(Long fileId) {
        return urlRepository.findAllByFileInfoId(fileId);
    }

    @Override
    public void download(Long urlId) {
        Url url = urlRepository.getById(urlId);
    }
}
