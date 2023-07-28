package com.example.halagodainv.repository;

import com.example.halagodainv.dto.hompage.EfficiencyOptimizationDto;
import com.example.halagodainv.dto.hompage.OriginativeDto;
import com.example.halagodainv.model.EfficiencyOptimizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EfficiencyOptimizationRepository extends JpaRepository<EfficiencyOptimizationEntity, Long> {

    @Query("select new com.example.halagodainv.dto.hompage.EfficiencyOptimizationDto(eo.title,eo.content) from EfficiencyOptimizationEntity eo " +
            "where eo.language =:language and (eo.id =1 or eo.id = 2)")
    EfficiencyOptimizationDto getEfficiencyOptiLanguage(@Param("language") String language);
    @Query("select eo from EfficiencyOptimizationEntity eo " +
            "where eo.id =1 or eo.id = 2")
    List<EfficiencyOptimizationEntity> getEfficiencyOptiLanguage();

    @Query("select new com.example.halagodainv.dto.hompage.OriginativeDto(eo.title,eo.content) from EfficiencyOptimizationEntity eo " +
            "where eo.language =:language and (eo.id =3 or eo.id = 4)")
    OriginativeDto getOriginativeLanguage(@Param("language") String language);

    @Query("select eo from EfficiencyOptimizationEntity eo " +
            "where eo.id =3 or eo.id = 4")
    List<EfficiencyOptimizationEntity> getOriginativeLanguage();
}
