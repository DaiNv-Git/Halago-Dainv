package com.example.halagodainv.dto.news;

import com.example.halagodainv.model.NewsEntity;
import com.example.halagodainv.model.NewsLanguageEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewDtoLanguage {
    private String title;
    private String content;
    private String language;
    private String image;

    public NewDtoLanguage(NewsLanguageEntity entity) {
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.language = entity.getLanguage();
    }
}
