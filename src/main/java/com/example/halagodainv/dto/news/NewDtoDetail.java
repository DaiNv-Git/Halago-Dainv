package com.example.halagodainv.dto.news;

import lombok.Data;

import java.util.List;

@Data
public class NewDtoDetail {
    private int id;
    private String title;
    private String category;
    private String image;

    public NewDtoDetail(int id, String title, String image, String type) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.category = type;
    }
}
