package com.example.halagodainv.dto.story;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoryMediaDto {
    private long id;
    private String image;
    private String title;
    private String content;
    private String contentDetail;
}
