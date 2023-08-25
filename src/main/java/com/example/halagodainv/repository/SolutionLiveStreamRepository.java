package com.example.halagodainv.repository;

import com.example.halagodainv.dto.solution.livestream.SolutionLiveStreamMapEntity;
import com.example.halagodainv.model.SolutionLiveStreamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SolutionLiveStreamRepository extends JpaRepository<SolutionLiveStreamEntity, Long> {

    @Query("select new com.example.halagodainv.dto.solution.livestream.SolutionLiveStreamMapEntity(s.live,s.brand,s.money,s.imageSale1,s.imageSale2) from SolutionLiveStreamEntity s " +
            "where s.id =1 ")
    SolutionLiveStreamMapEntity getBySolution();
}
