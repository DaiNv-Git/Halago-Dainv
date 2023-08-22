package com.example.halagodainv.dto.viewnews;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewNewsMap {
    private int id;
    private String title;
    private String herder;
    private String body;
    private String footer;
    private String image1;
    private String image2;
    private String createdDate;
    private Long topicId;
    private Long tagId;

    public ViewNewsMap(int id, String title, String herder, String body, String footer, String image1, String image2, Date createdDate, Long topicId, Long tagId) {
        this.id = id;
        this.title = title;
        this.herder = herder;
        this.body = body;
        this.footer = footer;
        this.image1 = image1;
        this.image2 = image2;
        this.createdDate = DateFormatUtils.format(createdDate, "yyyy-MM-dd");
        this.topicId = topicId;
        this.tagId = tagId;
    }
}
