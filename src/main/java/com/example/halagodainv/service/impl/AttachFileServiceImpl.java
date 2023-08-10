package com.example.halagodainv.service.impl;

import com.example.halagodainv.model.AttachFileEntity;
import com.example.halagodainv.repository.AttachFileRepository;
import com.example.halagodainv.service.AttachFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class AttachFileServiceImpl implements AttachFileService {
    private final AttachFileRepository attachFileRepository;

    public void uploadFile(MultipartFile file) throws IOException {
        String uploadFile = StringUtils.getFilenameExtension(file.getOriginalFilename());
        if (uploadFile.toUpperCase(Locale.ROOT).equals("PNG") || uploadFile.toUpperCase(Locale.ROOT).equals("JPG") || uploadFile.toUpperCase(Locale.ROOT).equals("SVG")) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            AttachFileEntity attachFileEntity = new AttachFileEntity();
            attachFileEntity.setCreatedDate(new Date());
            attachFileEntity.setStatus(200);
            attachFileEntity.setFileName(fileName);
            attachFileEntity.setTypeFIle(file.getContentType());
            attachFileEntity.setUrlFile(file.getBytes());
            attachFileRepository.save(attachFileEntity);
        }
    }
}
