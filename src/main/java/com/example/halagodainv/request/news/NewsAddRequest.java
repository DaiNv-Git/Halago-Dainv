package com.example.halagodainv.request.news;

import lombok.Data;

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
    private Integer status;
    private String photoTitle;
    private String linkPost;

}
