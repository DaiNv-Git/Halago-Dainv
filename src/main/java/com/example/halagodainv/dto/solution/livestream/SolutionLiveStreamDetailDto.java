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
    SolutionLiveStreamMapEntity solutionLiveStreamMapEntity;
    List<ImageSolutionDto> solutionDtos = new ArrayList<>();
}
