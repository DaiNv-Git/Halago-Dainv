package com.example.halagodainv.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class ImageFileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String filePath;
    private String base64;
}
