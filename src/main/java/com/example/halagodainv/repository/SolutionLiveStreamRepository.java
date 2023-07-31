package com.example.halagodainv.repository;

import com.example.halagodainv.model.SolutionLiveStreamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolutionLiveStreamRepository extends JpaRepository<SolutionLiveStreamEntity,Long> {
}
