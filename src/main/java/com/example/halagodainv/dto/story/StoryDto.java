package com.example.halagodainv.dto.story;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoryDto {
    private String live;
    private String brand;
    private String money;
    List<StoryMediaDto> storyMediaDtos = new ArrayList<>();
}
