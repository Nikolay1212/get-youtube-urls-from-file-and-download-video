package com.digit.downloader.dto;

import com.digit.downloader.model.Url;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class UrlDto {
    private Long id;
    private String url;

    public static UrlDto from(Url url) {
        return UrlDto.builder()
                .id(url.getId())
                .url(url.getUrl())
                .build();
    }

    public static List<UrlDto> from(List<Url> urls) {
        return urls.stream().map(UrlDto::from).collect(Collectors.toList());
    }
}
