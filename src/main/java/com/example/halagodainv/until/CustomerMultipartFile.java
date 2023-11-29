package com.example.halagodainv.until;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Base64;

@Data
@AllArgsConstructor
public class CustomerMultipartFile {
    public static MultipartFile convertBase64ToMultipartFile(String base64String, String filename, String contentType) throws IOException {
        byte[] decodedBytes = Base64.getDecoder().decode(base64String);

        ByteArrayResource resource = new ByteArrayResource(decodedBytes);

        return new CustomMultipartFile(resource, filename, contentType);
    }

    private static class CustomMultipartFile implements MultipartFile {

        private final ByteArrayResource resource;
        private final String filename;
        private final String contentType;

        public CustomMultipartFile(ByteArrayResource resource, String filename, String contentType) {
            this.resource = resource;
            this.filename = filename;
            this.contentType = contentType;
        }

        @Override
        public String getName() {
            return null;
        }

        @Override
        public String getOriginalFilename() {
            return filename;
        }

        @Override
        public String getContentType() {
            return contentType;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public long getSize() {
            return resource.contentLength();
        }

        @Override
        public byte[] getBytes() throws IOException {
            return resource.getByteArray();
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return resource.getInputStream();
        }

        @Override
        public Resource getResource() {
            return MultipartFile.super.getResource();
        }

        @Override
        public void transferTo(File dest) throws IOException, IllegalStateException {
        }

        @Override
        public void transferTo(Path dest) throws IOException, IllegalStateException {
            MultipartFile.super.transferTo(dest);
        }
    }
}
