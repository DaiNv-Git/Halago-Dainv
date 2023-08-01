package com.example.halagodainv.request.solution.review;

import lombok.Data;

import java.util.List;

@Data
public class SolutionReviewRequestEdit {
    private List<SolutionReviewEdit> solutionReview;
    private List<SolutionReviewEditImage> solutionReviewEditImages;
}
