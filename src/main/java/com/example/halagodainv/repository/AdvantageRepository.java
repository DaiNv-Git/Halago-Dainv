package com.example.halagodainv.repository;

import com.example.halagodainv.dto.hompage.AdvantageDto;
import com.example.halagodainv.dto.hompage.AdvantageMapEntityDto;
import com.example.halagodainv.model.AdvantageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvantageRepository extends JpaRepository<AdvantageEntity, Long> {

    @Query(value = "select new com.example.halagodainv.dto.hompage.AdvantageMapEntityDto(at.title,at.content,adl.title,adl.content) from AdvantageEntity at " +
            "left join AdvantageEntityLanguage adl on adl.advanId=at.id order by at.created desc ")
    List<AdvantageMapEntityDto> getAdvantageLanguage();

    @Query(value = "select at from AdvantageEntity at " +
            "order by at.created desc ")
    List<AdvantageEntity> getAdvantageAll();
}
