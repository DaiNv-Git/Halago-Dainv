package com.example.halagodainv.dto.solution.livestream;

import com.example.halagodainv.dto.solution.livestream.ImageSolutionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolutionLiveStreamDTO {
    private String session;
    private String satisfiedBrand;
    private String sales;
    private String imageSale1;
    private String imageSale2;
    private String contentOne;
    private String contentTwo;
    private String contentThree;
    List<ImageSolutionDto> solutionDtos = new ArrayList<>();
}
