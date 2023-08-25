package com.example.halagodainv.dto.solution.livestream;

import com.example.halagodainv.model.ImageLiveStreamEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolutionLiveStreamDetailDto {
    private String live;
    private String brand;
    private String money;
    private String imageSale1;
    private String imageSale2;
    List<ImageSolutionDetailDto> imgSlider = new ArrayList<>();

    public SolutionLiveStreamDetailDto(SolutionLiveStreamMapEntity map, List<ImageSolutionDetailDto> entities) {
        this.live = map.getLive();
        this.brand = map.getBrand();
        this.money = map.getMoney();
        this.imageSale1 = map.getImageSale1();
        this.imageSale2 = map.getImageSale2();
        this.imgSlider = entities;
    }
}
