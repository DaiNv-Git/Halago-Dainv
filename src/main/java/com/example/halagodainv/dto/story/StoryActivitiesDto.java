package com.example.halagodainv.dto.story;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoryActivitiesDto {
    private long id;
    private String image;
    private String title;
    private String content;
}
