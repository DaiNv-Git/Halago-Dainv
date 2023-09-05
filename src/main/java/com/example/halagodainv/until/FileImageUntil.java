package com.example.halagodainv.until;

import com.example.halagodainv.model.ImageFileEntity;
import com.example.halagodainv.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

@Component
public class FileImageUntil {
    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
    private ImageRepository imageRepository;

    public String uploadImage(MultipartFile file) throws IOException {
        try {
            // Lưu tệp vào thư mục tĩnh
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path path = Paths.get(uploadPath + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            // Lưu thông tin tệp vào cơ sở dữ liệu
            ImageFileEntity image = new ImageFileEntity();
            image.setFileName(fileName);
            image.setFilePath(path.toString());
            imageRepository.save(image);
            return fileName;
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }
}
