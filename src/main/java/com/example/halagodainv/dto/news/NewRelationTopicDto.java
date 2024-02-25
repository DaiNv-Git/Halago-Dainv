package com.example.halagodainv.dto.news;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewRelationTopicDto {
    private int newId;
    private String title;
    private String img;
    private String created;
}
