package com.example.halagodainv.dto.solution.review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageReviewDto {
    private long Id;
    private String image;
    private String link;

}
