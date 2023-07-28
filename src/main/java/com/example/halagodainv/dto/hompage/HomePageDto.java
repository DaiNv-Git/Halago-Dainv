package com.example.halagodainv.dto.hompage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomePageDto {
    OriginativeDto originative;
    List<AdvantageDto> advantages;
    EfficiencyOptimizationDto efficiencyOptimizations;
    List<NewsTenDto> halagoProject;

}
