package com.digit.downloader.service;

import com.digit.downloader.dto.UrlDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    Long save(MultipartFile file) throws IOException;
    List<UrlDto> getAllUrlFromFile(Long id);
}
