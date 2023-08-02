package com.example.halagodainv.repository;

import com.example.halagodainv.dto.solution.livestream.ImageSolutionDto;
import com.example.halagodainv.model.ImageLiveStreamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageSolutionRepository extends JpaRepository<ImageLiveStreamEntity, Long> {
    @Query("select new com.example.halagodainv.dto.solution.livestream.ImageSolutionDto(i.image) from ImageLiveStreamEntity i where i.solutionLiveStreamId = 1")
    List<ImageSolutionDto> getAllImage();
}
