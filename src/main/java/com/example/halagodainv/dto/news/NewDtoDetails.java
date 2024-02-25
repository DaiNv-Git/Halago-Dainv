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
    private String photoTitle;
    private String linkPost;
    private Boolean isHot;
    private Boolean isView;
    private String authorName;
    private String authorAvatar;
    private Long topicId;
    private String topicName;
    private List<Integer> tagId;
    private String tagNames;

}
