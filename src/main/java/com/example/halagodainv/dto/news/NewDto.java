package com.example.halagodainv.dto.news;

import lombok.Data;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.util.Date;

@Data
public class NewDto {
    private int id;
    private String title;
    private String category;
    private String image;
    private String created;

    public NewDto(int id, String title, String image, String type, Date created) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.category = type;
        this.created = DateFormatUtils.format(created, "dd-MM-yyyy");
    }
}
