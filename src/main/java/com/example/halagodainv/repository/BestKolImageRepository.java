package com.example.halagodainv.repository;

import com.example.halagodainv.dto.kol.BestPickDto;
import com.example.halagodainv.model.BestKolImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BestKolImageRepository extends JpaRepository<BestKolImageEntity, Long> {

    @Query("select new com.example.halagodainv.dto.kol.BestPickDto(bi.id,bi.image) from BestKolImageEntity bi ")
    List<BestPickDto> getAllImage();
}
