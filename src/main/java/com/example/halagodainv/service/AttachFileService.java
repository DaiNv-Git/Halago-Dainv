package com.example.halagodainv.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AttachFileService {
    void uploadFile(MultipartFile file) throws IOException;
}
