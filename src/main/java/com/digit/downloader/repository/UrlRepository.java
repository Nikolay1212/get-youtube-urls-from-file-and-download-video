package com.digit.downloader.repository;

import com.digit.downloader.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UrlRepository extends JpaRepository<Url, Long> {
    List<Url> findAllByFileInfoId(Long fileId);
}
