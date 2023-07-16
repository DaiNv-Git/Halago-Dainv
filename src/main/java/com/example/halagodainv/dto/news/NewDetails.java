package com.example.halagodainv.dto.news;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewDetails {
    private Integer idNews;
    private String title;
    private String thumbnail;
    private String description;
    private String content;
    private Integer type;
    private Integer status;
    private String photoTitle;
    private String linkPost;
    private String language;

}
