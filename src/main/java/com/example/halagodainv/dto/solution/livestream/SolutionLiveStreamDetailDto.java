package com.example.halagodainv.dto.solution.livestream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolutionLiveStreamDetailDto {
    private String session;
    private String satisfiedBrand;
    private String sales;
    private String imageSale1;
    private String imageSale2;
    private String contentOne;
    private String contentTwo;
    private String contentThree;
    private String contentOneEN;
    private String contentTwoEN;
    private String contentThreeEN;
    List<ImageSolutionDto> imagEdits = new ArrayList<>();

    public SolutionLiveStreamDetailDto(SolutionLiveStreamMapEntity map, List<ImageSolutionDto> solutionDtos) {
        this.session = map.getSession();
        this.satisfiedBrand = map.getSatisfiedBrand();
        this.sales = map.getSales();
        this.imageSale1 = map.getImageSale1();
        this.imageSale2 = map.getImageSale2();
        this.contentOne = map.getContentOne();
        this.contentTwo = map.getContentTwo();
        this.contentThree = map.getContentThree();
        this.contentOneEN = map.getContentOne();
        this.contentTwoEN = map.getContentTwoEN();
        this.contentThreeEN = map.getContentThreeEN();
        this.imagEdits = solutionDtos;
    }
}
