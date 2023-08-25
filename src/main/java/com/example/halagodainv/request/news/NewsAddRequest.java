package com.example.halagodainv.request.news;

import lombok.Data;

import javax.persistence.Column;

@Data
public class NewsAddRequest {
    private Integer idNews;
    private String titleVN;
    private String titleEN;
    private String thumbnail;
    private String descriptionVN;
    private String descriptionEN;
    private String contentVN;
    private String contentEN;
    private Integer type;
    private String photoTitle;
    private String linkPost;
    private String herderVN;
    private String herderEN;
    private String bodyVN;
    private String bodyEN;
    private String footerVN;
    private String footerEN;
    private String image1;
    private String image2;
    private Long topicId;
    private Long tagId;
    private Boolean isHot =false;

}
