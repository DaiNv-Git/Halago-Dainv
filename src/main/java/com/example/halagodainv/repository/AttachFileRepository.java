package com.example.halagodainv.repository;

import com.example.halagodainv.model.AttachFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachFileRepository extends JpaRepository<AttachFileEntity, Long> {
}
