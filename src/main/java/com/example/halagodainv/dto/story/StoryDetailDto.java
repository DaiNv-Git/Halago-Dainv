package com.example.halagodainv.dto.story;

import com.example.halagodainv.model.StoryHalagoEntity;
import com.example.halagodainv.model.StoryTypicalActivitiesEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoryDetailDto {
    private String img;
    private String content;
    private String contentEN;
}
