package com.example.halagodainv.dto.news;

import lombok.Data;


@Data
public class NewDto {
    private int id;
    private String title;
    private String img;
    private String created;
    private String tagNames;
    private String topicName;
}
