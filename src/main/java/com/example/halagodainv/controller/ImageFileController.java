package com.example.halagodainv.controller;

import com.example.halagodainv.model.ImageFileEntity;
import com.example.halagodainv.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/images")
public class ImageFileController {
    @Value("${upload.path}")
    private String uploadPath;

//    @PostMapping("/upload")
//    public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
//        try {
//            // Lưu tệp vào thư mục tĩnh
//            String fileExtension = getFileExtension(file.getOriginalFilename());
//            System.out.println("============" + file.getOriginalFilename());
//            String uniqueFileName = UUID.randomUUID() + fileExtension;
//            Path path = Paths.get(uploadPath + uniqueFileName);
//            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//
//            // Tạo URL HTTP công cộng cho tệp tải lên
//            String publicImageUrl = "https://www.halago.vn/halago/" + uniqueFileName;
//
//            // Lưu thông tin tệp vào cơ sở dữ liệu (nếu cần)
//            ImageFileEntity image = new ImageFileEntity();
//            image.setFileName(uniqueFileName);
//            image.setFilePath(publicImageUrl);
//            imageRepository.save(image);
//
//            return publicImageUrl;
//        } catch (IOException e) {
//            throw new IOException(e.getMessage());
//        }
//    }

    @GetMapping("/get/{fileName:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String fileName) {
        try {
            // Đọc tệp từ thư mục tĩnh
            Path filePath = Paths.get(uploadPath + fileName);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                // Xác định loại nội dung dựa trên phần mở rộng của tệp
                String fileExtension = StringUtils.getFilenameExtension(fileName);
                String contentType = getContentType(fileExtension);

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
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
