package com.example.halagodainv.request.solution.review;

import com.example.halagodainv.request.solution.review.SolutionReviewEdit;
import lombok.Data;

import java.util.List;

@Data
public class SolutionReviewRequestEdit {
    private List<SolutionReviewEdit> solutionReviewEdits;
    private List<SolutionReviewEditImage> solutionReviewEditImages;
}
