package com.example.halagodainv.repository;

import com.example.halagodainv.model.AboutUsLanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutUsLanguageRepository extends JpaRepository<AboutUsLanguageEntity, Integer> {

}
