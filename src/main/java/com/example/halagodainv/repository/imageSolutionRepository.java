package com.example.halagodainv.repository;

import com.example.halagodainv.model.ImageSolution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface imageSolutionRepository extends JpaRepository<ImageSolution, Long> {
}
