package com.example.halagodainv.dto.news;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewDtoDetails {
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

}
