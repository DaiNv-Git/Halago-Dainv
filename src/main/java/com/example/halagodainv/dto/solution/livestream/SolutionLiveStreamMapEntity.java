package com.example.halagodainv.dto.solution.livestream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolutionLiveStreamMapEntity {
    private String live;
    private String brand;
    private String money;
    private String imageSale1;
    private String imageSale2;
    List<ImageSolutionDto> imgSlider = new ArrayList<>();

    public SolutionLiveStreamMapEntity(String live, String brand, String money, String imageSale1, String imageSale2) {
        this.live = live;
        this.brand = brand;
        this.money = money;
        this.imageSale1 = imageSale1;
        this.imageSale2 = imageSale2;
    }
}
