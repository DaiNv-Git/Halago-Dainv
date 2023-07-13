package com.example.halagodainv.dto.news;

import com.example.halagodainv.model.NewsEntity;
import com.example.halagodainv.model.NewsLanguageEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewDto {
    private int id;
    private String title;
    private String thumbnail;
    public NewDto(NewsEntity newsEntity, List<NewsLanguageEntity> newsLanguageEntity, String language) {
        for (NewsLanguageEntity entity : newsLanguageEntity) {
            if (entity.getLanguage().equalsIgnoreCase(language) && entity.getNewsEntity().getIdNews() == newsEntity.getIdNews()) {
                this.id = newsEntity.getIdNews();
                this.title = entity.getTitle();
                this.thumbnail = newsEntity.getThumbnail();
            }
        }
    }

}
