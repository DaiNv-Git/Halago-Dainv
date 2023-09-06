package com.example.halagodainv.until;

import com.example.halagodainv.model.ImageFileEntity;
import com.example.halagodainv.repository.ImageRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;


@Component
public class FileImageUtil {
    @Value("${upload.path}")
    private String uploadPath;

    @Value("${call.path}")
    private String callFile;
    @Autowired
    private ImageRepository imageRepository;

    public String uploadImage(String base64Data) {
        if (Strings.isBlank(base64Data)) {
            return "";
        }
        Optional<ImageFileEntity> existingImage = imageRepository.findByFilePath(base64Data);
        if (existingImage.isEmpty()) {
            Optional<ImageFileEntity> existingImageBase64 = imageRepository.findByBase64(base64Data);
            if (existingImageBase64.isEmpty()) {
                String[] parts = base64Data.split(",");
                String base64 = parts[1];
                String contentType = HtmlUtils.htmlEscape(parts[0].split(":")[1]);
                String extension = getFileExtension(contentType);
                byte[] decodedBytes = Base64.getDecoder().decode(base64);
                String uniqueFileName = UUID.randomUUID() + "." + extension;
                Path path = Paths.get(uploadPath + uniqueFileName);
                try {
                    Files.write(path, decodedBytes);
                    String publicImageUrl = callFile + uniqueFileName;
                    ImageFileEntity image = new ImageFileEntity();
                    image.setFileName(uniqueFileName);
                    image.setFilePath(publicImageUrl);
                    image.setBase64(base64Data);
                    imageRepository.save(image);
                    return publicImageUrl;
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error occurred while saving the image.";
                }
            } else {
                return existingImageBase64.get().getFilePath();
            }
        } else {
            return existingImage.get().getFilePath();
        }
    }

    private String getFileExtension(String fileName) {
        String[] parts = fileName.split("/");

// Lấy phần tử cuối cùng sau khi tách chuỗi
        String fileTypeWithBase64 = parts[1];

// Tách phần đuôi file (vd: "png;base64") bằng dấu ";"
        String[] fileTypeParts = fileTypeWithBase64.split(";");
        return fileTypeParts[0];
    }
}
