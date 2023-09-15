package com.example.halagodainv.controller;

import com.example.halagodainv.model.ImageFileEntity;
import com.example.halagodainv.repository.ImageRepository;
import com.example.halagodainv.until.FileImageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/images")
public class ImageFileController {
    @Autowired
    private ImageRepository imageRepository;

    @GetMapping("/get/{fileName}")
    public ResponseEntity<?> getImage(@PathVariable String fileName) {
        Optional<ImageFileEntity> dbImageData = imageRepository.findByFileName(fileName);
        if (dbImageData.isPresent()) {
            byte[] images = FileImageUtil.decompressImage(dbImageData.get().getImageData());
            String fileExtension = StringUtils.getFilenameExtension(fileName);
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(getContentType(fileExtension))).body(images);
        }
        return ResponseEntity.status(HttpStatus.OK).body("");
    }


    private String getContentType(String fileExtension) {
        // Ánh xạ các phần mở rộng với loại nội dung tương ứng
        Map<String, String> extensionContentTypeMap = new HashMap<>();
        extensionContentTypeMap.put("jpg", "image/jpeg");
        extensionContentTypeMap.put("jpeg", "image/jpeg");
        extensionContentTypeMap.put("png", "image/png");
        extensionContentTypeMap.put("gif", "image/gif");
        // Nếu không xác định được phần mở rộng, mặc định sử dụng loại nội dung 'application/octet-stream'
        return extensionContentTypeMap.getOrDefault(fileExtension, "application/octet-stream");
    }

}
