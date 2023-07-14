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

}
