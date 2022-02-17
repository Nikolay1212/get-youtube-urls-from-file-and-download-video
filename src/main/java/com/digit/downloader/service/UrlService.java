package com.digit.downloader.service;

import com.digit.downloader.model.Url;

import java.util.List;

public interface UrlService {
    void save(List<Url> urls);
    List<Url> getAllUrls();
    List<Url> getUrlsFromFile(Long fileId);
    void download(Long urlId);
}
