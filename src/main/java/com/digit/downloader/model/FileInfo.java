package com.digit.downloader.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;
    private String storageFileName;
    private String path;
    private String ordinalName;
    private String type;
    private Long size;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Url> urls;
}
