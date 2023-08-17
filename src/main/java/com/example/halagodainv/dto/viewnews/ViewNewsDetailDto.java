package com.example.halagodainv.dto.viewnews;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewNewsDetailDto {
    private int id;
    private String title;
    private String herder;
    private String body;
    private String footer;
    private String image1;
    private String image2;
    private String createdDate;
}
