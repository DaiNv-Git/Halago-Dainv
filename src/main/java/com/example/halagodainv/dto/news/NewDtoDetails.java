package com.example.halagodainv.dto.news;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewDtoDetails {
    private Integer idNews;
    private String titleVN;
    private String titleEN;
    private String img;
    private String descriptionVN;
    private String descriptionEN;
    private String contentVN;
    private String contentEN;
    private Integer type;
    private Integer status;
    private String photoTitle;
    private String linkPost;
    private Long topicId;
    private Boolean isHot;
    private Boolean isView;
    private List<Integer> tagId;

}
