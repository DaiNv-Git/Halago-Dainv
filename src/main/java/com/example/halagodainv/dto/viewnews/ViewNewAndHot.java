package com.example.halagodainv.dto.viewnews;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ViewNewAndHot {
    private int id;
    private String title;
    private String image;

    public ViewNewAndHot(int id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }
}
