package com.example.halagodainv.repository;

import com.example.halagodainv.dto.solution.review.ImageReviewDto;
import com.example.halagodainv.model.ImageReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageSolutionReviewRepository extends JpaRepository<ImageReviewEntity, Long> {
    @Query("select new com.example.halagodainv.dto.solution.review.ImageReviewDto(img.imageReview,img.nameVN,img.nameEN,img.link) from ImageReviewEntity img ")
    List<ImageReviewDto> getImages();
};
