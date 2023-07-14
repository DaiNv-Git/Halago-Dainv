package com.example.halagodainv.dto.news;

import com.example.halagodainv.model.NewsEntity;
import com.example.halagodainv.model.NewsLanguageEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewDto {
    private int id;
    private String thumbnail;
    private List<NewDtoLanguage> newDtoLanguages = new ArrayList<>();

    public NewDto(NewsEntity newsEntity, List<NewsLanguageEntity> newsLanguageEntity) {
        this.id = newsEntity.getIdNews();
        this.thumbnail = newsEntity.getThumbnail();
        newsLanguageEntity.forEach(i -> {
            this.newDtoLanguages.add(new NewDtoLanguage(i));
        });
    }

}
