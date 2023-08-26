package com.example.halagodainv.request.news;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NewsAddRequest {
    private Integer idNews;
    private String titleVN;
    private String titleEN;
    private String img;
    private String descriptionVN;
    private String descriptionEN;
    private String contentVN;
    private String contentEN;
    private Integer type;
    private String photoTitle;
    private String linkPost;
    private Long topicId;
    private List<Integer> tagId = new ArrayList<>();
    private Boolean isHot =false;
    private Boolean isView =false;

}
