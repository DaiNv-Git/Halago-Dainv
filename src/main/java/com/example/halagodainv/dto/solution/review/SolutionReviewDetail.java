package com.example.halagodainv.dto.solution.review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolutionReviewDetail {
    List<SolutionReviewMapEntity> solutionReview = new ArrayList<>();
    List<ImageReviewDto> project = new ArrayList<>();
}
