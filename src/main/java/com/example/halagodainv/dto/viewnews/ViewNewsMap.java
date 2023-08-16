package com.example.halagodainv.dto.viewnews;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

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
}
