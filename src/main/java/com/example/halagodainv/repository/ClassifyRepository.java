package com.example.halagodainv.repository;

import com.example.halagodainv.model.ClassifyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassifyRepository extends JpaRepository<ClassifyEntity, Integer> {
    List<ClassifyEntity> findByIdIn(List<Integer> ids);
    List<ClassifyEntity> findByNameIn(List<String> names);
}
