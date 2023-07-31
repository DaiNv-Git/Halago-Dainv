package com.example.halagodainv.repository;

import com.example.halagodainv.dto.solution.livestream.SolutionLiveStreamMapEntity;
import com.example.halagodainv.model.SolutionLiveStreamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SolutionLiveStreamRepository extends JpaRepository<SolutionLiveStreamEntity, Long> {

    @Query("select new com.example.halagodainv.dto.solution.livestream.SolutionLiveStreamMapEntity(s.session,s.satisfiedBrand,s.sales,s.imageSale1,s.imageSale2,s.contentOne,s.contentTwo,s.contentThree,sl.contentOneEN,sl.contentTwoEN,sl.contentThreeEN) from SolutionLiveStreamEntity s " +
            "left join SolutionLiveStreamLanguageEntity sl on s.id = sl.solutionId where s.id =1 ")
    SolutionLiveStreamMapEntity getBySolution();
}
