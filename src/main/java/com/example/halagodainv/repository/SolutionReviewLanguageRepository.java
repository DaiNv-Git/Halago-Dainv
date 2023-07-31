package com.example.halagodainv.repository;

import com.example.halagodainv.model.SolutionReivewLanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SolutionReviewLanguageRepository extends JpaRepository<SolutionReivewLanguageEntity, Long> {

    Optional<SolutionReivewLanguageEntity> findBySolutionReviewId(long Id);
}
