package com.example.halagodainv.request.solution.livestream;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SolutionLiveStreamEdit {
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
    List<SolutionLiveStreamImageEdit> imagEdits = new ArrayList<>();
}
