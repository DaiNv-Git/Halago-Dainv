package com.example.halagodainv.repository;

import com.example.halagodainv.dto.hompage.EfficiencyOptimizationDto;
import com.example.halagodainv.model.EfficiencyOptimizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EfficiencyOptimizationRepository extends JpaRepository<EfficiencyOptimizationEntity, Long> {

    @Query("select new com.example.halagodainv.dto.hompage.EfficiencyOptimizationDto(eo.title,eo.content) from EfficiencyOptimizationEntity eo " +
            "where eo.language =:language")
    EfficiencyOptimizationDto getEfficiencyOptiLanguage(@Param("language") String language);
}
