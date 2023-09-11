package com.example.halagodainv.repository;

import com.example.halagodainv.model.ImageFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<ImageFileEntity,Long> {
    Optional<ImageFileEntity> findByFileName(String fileName);
    Optional<ImageFileEntity> findByFilePath(String filePath);
    Optional<ImageFileEntity> findByBase64(byte[] base64);
}
