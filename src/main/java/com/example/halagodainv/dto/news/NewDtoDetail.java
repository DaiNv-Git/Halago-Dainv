package com.example.halagodainv.dto.news;

import com.example.halagodainv.model.NewsEntity;
import com.example.halagodainv.model.NewsLanguageEntity;
import lombok.Data;

import java.util.List;

@Data
public class NewDtoDetail {
    private String title;
    private String content;
    private String description;
    private String thumbnail;
    private int status;
    private int type;

    public NewDtoDetail(NewsEntity newsEntity, List<NewsLanguageEntity> newsLanguageEntities) {
        newsLanguageEntities.forEach(i -> {
            this.title = i.getTitle();
            this.content = i.getContent();
            this.description = i.getDescription();
        });
        this.status = newsEntity.getStatus();
        this.type = newsEntity.getType();
        this.thumbnail = newsEntity.getThumbnail();

    }
}
