package com.example.halagodainv.repository;

import com.example.halagodainv.model.SolutionLiveStreamLanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolutionLiveStreamLanguageRepository extends JpaRepository<SolutionLiveStreamLanguageEntity,Long> {
}
