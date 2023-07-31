package com.example.halagodainv.dto.solution.review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolutionReviewMapEntity {
    private long Id;
    private String title;
    private String content;
    private String contentDetail;
    private String titleEN;
    private String contentEN;
    private String contentDetailEN;
}
