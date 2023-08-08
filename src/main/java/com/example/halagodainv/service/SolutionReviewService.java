package com.example.halagodainv.service;

import com.example.halagodainv.request.solution.review.SolutionReviewEditImage;
import com.example.halagodainv.request.solution.review.SolutionReviewRequestEdit;

import java.util.List;

public interface SolutionReviewService {
    Object getAll(String language);

    Object getDetail();

    Object updateImageReview(List<SolutionReviewEditImage> images);

    Object updateContent(SolutionReviewRequestEdit edit);
}
