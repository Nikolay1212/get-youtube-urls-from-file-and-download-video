package com.digit.downloader.controller;

import com.digit.downloader.service.Downloader;
import com.digit.downloader.service.FileService;
import com.digit.downloader.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private UrlService urlService;

    @Autowired
    private Downloader downloader;

    @GetMapping("/file_upload")
    public String getFileUploadPage() {
        return "file_upload";
    }

    @PostMapping("/file_upload")
    public String saveFile(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        Long fileId = fileService.save(file);
        model.addAttribute("urls", urlService.getUrlsFromFile(fileId));
        model.addAttribute("fileId", fileId);
        return "urls";
    }

    @GetMapping("/urls/download")
    public String getDownloadPage() {
        System.out.println("download");
        return "download";
    }

    @GetMapping("/urls/download/{id}")
    public String singleDownload(@PathVariable Long id) {
        System.out.println(id + " idid");
        System.out.println(downloader.downloadOneVideoFromFile(id));
        return "download";
    }

    @GetMapping("/urls/download/all/{fileId}")
    public String downloadAllVideo(@PathVariable Long fileId) {
        downloader.downloadAllVideoFromFile(fileId);
        return "download";
    }
}
