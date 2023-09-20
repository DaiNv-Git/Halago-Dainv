package com.example.halagodainv.dto.hompage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsTenDto {
    private String titleImage;
    private String linkPagers;
    private String title;
    private String image;
    private String description;
    private String created;
    private String avatar;
    private String nameAuthor;

    public NewsTenDto(String linkPagers, String titleImage, String title, String image, String description, Date created, String avatar, String name) {
        this.titleImage = titleImage;
        this.linkPagers = linkPagers;
        this.title = title;
        this.image = image;
        this.description = description;
        this.created = DateFormatUtils.format(created, "yyyy-MM-dd HH:ss aa");
        this.avatar = avatar;
        this.nameAuthor = name;
    }
}
