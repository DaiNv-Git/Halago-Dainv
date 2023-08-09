package com.example.halagodainv.controller;

import com.example.halagodainv.filebase.FirebaseStoreManager;
import com.example.halagodainv.service.AttachFileService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.service.ResponseMessage;

import java.nio.file.FileAlreadyExistsException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {
    Logger logger = LoggerFactory.getLogger(FileController.class);
    private final FirebaseStoreManager attachFileService;

    @PostMapping("")
    public Object uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            attachFileService.uploadFile(file);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(200, "success", null, null, null));
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }
            throw new RuntimeException(e.getMessage());
        }
    }
}
