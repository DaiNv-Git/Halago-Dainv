package com.example.halagodainv.repository;

import com.example.halagodainv.dto.kol.RepresentativeMapEntity;
import com.example.halagodainv.model.RepresentativeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepresentativesRepository extends JpaRepository<RepresentativeEntity, Long> {

    @Query("select new com.example.halagodainv.dto.kol.RepresentativeMapEntity(b.img,b.img2,b.name,b.content,b.reach,b.interactions,b.interactionRate,b.nameEN,b.contentEN) from RepresentativeEntity b ")
    List<RepresentativeMapEntity> getAll();
}
