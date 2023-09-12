package com.example.halagodainv.until;

import com.example.halagodainv.model.ImageFileEntity;
import com.example.halagodainv.repository.ImageRepository;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Optional;
import java.util.zip.Deflater;
import java.util.zip.Inflater;


@Component
public class FileImageUtil {
    @Value("${upload.path}")
    private String uploadPath;

    @Value("${call.path}")
    private String callFile;

    @Value("${call.path.local}")
    private String callFileLocal;
    @Autowired
    private ImageRepository imageRepository;

    public String uploadImage(String base64Data) {
        if (Strings.isBlank(base64Data)) {
            return "";
        }
        Optional<ImageFileEntity> existingImage = imageRepository.findByFilePath(base64Data);
        if (existingImage.isEmpty()) {
            String[] parts = base64Data.split(",");
            String base64 = parts[1];
            byte[] decodedData = Base64.getDecoder().decode(base64);
            Optional<ImageFileEntity> existingImageBase64 = imageRepository.findByBase64(compressImage(decodedData));
            if (existingImageBase64.isEmpty()) {
                String contentType = HtmlUtils.htmlEscape(parts[0].split(":")[1]);
                String extension = getFileExtension(contentType);
                try {
                    ImageFileEntity image = new ImageFileEntity();
                    image.setFileName(readImageFile(base64, extension));
                    String publicImageUrl = callFile + image.getFileName();
                    image.setFilePath(publicImageUrl);
                    image.setBase64(compressImage(decodedData));
                    imageRepository.save(image);
                    return publicImageUrl;
                } catch (Exception e) {
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

    private static String readImageFile(String fileName, String contentType) {
        try {
            File tempFile = File.createTempFile("image-", "." + contentType);
            return tempFile.getName();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getFileExtension(String fileName) {
        String[] parts = fileName.split("/");
        String fileTypeWithBase64 = parts[1];
        String[] fileTypeParts = fileTypeWithBase64.split(";");
        return fileTypeParts[0];
    }


    public static byte[] compressImage(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[8 * 1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception ignored) {
        }
        return outputStream.toByteArray();
    }


    public static byte[] decompressImage(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[8 * 1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception ignored) {
        }
        return outputStream.toByteArray();
    }
}
