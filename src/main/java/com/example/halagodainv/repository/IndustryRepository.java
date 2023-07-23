package com.example.halagodainv.repository;


import com.example.halagodainv.model.IndustryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndustryRepository extends JpaRepository<IndustryEntity, Integer> {

    List<IndustryEntity> findByIdIn(List<Integer> ids);
    List<IndustryEntity> findByIndustryNameIn(List<String> industryNames);
}
