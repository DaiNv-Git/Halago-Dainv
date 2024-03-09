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
    private Integer type = 0;
    private String photoTitle;
    private String linkPost;
    private String authorName;
    private String authorAvatar;
    private Long topicId;
    private Long representativeId;
    private List<Integer> tagId;
    private Boolean isHot = false;

}
