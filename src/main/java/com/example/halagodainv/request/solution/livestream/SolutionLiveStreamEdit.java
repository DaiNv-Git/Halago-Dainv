package com.example.halagodainv.request.solution.livestream;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SolutionLiveStreamEdit {
    private String live;
    private String brand;
    private String money;
    private String imageSale1;
    private String imageSale2;
    List<SolutionLiveStreamImageEdit> imgSlider = new ArrayList<>();
}
