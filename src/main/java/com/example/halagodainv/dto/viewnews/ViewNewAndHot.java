package com.example.halagodainv.dto.viewnews;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ViewNewAndHot {
    private int id;
    private String title;
    private String image;
    private Date created;

    public ViewNewAndHot(int id, String title, String image,Date created) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.created = created;
    }
}
