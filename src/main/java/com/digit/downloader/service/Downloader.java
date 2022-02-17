package com.digit.downloader.service;

import java.io.File;

public interface Downloader {
    boolean downloadOneVideoFromFile(Long urlId);

    boolean downloadAllVideoFromFile(Long fileId);
}
