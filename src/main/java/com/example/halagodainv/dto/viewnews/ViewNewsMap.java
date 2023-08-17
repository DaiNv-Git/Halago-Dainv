package com.example.halagodainv.dto.viewnews;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.time.DateFormatUtils;

import javax.persistence.Column;
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
    private String titleEN;
    private String herderEN;
    private String bodyEN;
    private String footerEN;
    private String createdDate;

    public ViewNewsMap(int id, String title, String herder, String body, String footer, String image1, String image2, String titleEN, String herderEN, String bodyEN, String footerEN, Date createdDate) {
        this.id = id;
        this.title = title;
        this.herder = herder;
        this.body = body;
        this.footer = footer;
        this.image1 = image1;
        this.image2 = image2;
        this.titleEN = titleEN;
        this.herderEN = herderEN;
        this.bodyEN = bodyEN;
        this.footerEN = footerEN;
        this.createdDate = DateFormatUtils.format(createdDate, "yyyy-MM-dd");
    }
}
