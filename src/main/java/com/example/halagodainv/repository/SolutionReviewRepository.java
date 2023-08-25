package com.example.halagodainv.repository;

import com.example.halagodainv.dto.solution.review.SolutionReviewMapEntity;
import com.example.halagodainv.model.SolutionReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolutionReviewRepository extends JpaRepository<SolutionReviewEntity, Long> {
    @Query("select new com.example.halagodainv.dto.solution.review.SolutionReviewMapEntity(i.id,i.img,i.title,i.content,i.contentDetail,ie.titleEN,ie.contentEN,ie.contentDetailEN) from " +
            "SolutionReviewEntity i left join SolutionReivewLanguageEntity ie on i.id = ie.solutionReviewId ")
    List<SolutionReviewMapEntity> getByAll();
}
