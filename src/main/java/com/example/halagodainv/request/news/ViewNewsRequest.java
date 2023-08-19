package com.example.halagodainv.request.news;

import lombok.Data;

@Data
public class ViewNewsRequest {
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
    private Long topicId;
    private Long tagId;
}
