package com.example.halagodainv.repository;

import com.example.halagodainv.dto.kol.KolMapEntity;
import com.example.halagodainv.model.BestKolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BestKolRepository extends JpaRepository<BestKolEntity, Long> {

    @Query("select new com.example.halagodainv.dto.kol.KolMapEntity(bi.image,bi.name,bi.nameEN,bi.job,bi.jobEN) from BestKolEntity bi ")
    List<KolMapEntity> getAllImage();
}
