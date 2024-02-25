package com.example.halagodainv.dto.kol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepresentativeMapEntity {
    private Long id;
    private String img;
    private String img2;
    private String name;
    private String content;
    private String reach;
    private String interactions;
    private String interactionRate;
    private String nameEN;
    private String contentEN;
    private Long idNews;

    public RepresentativeMapEntity(Long id, String img, String img2, String name, String content, String reach, String interactions, String interactionRate, String nameEN, String contentEN) {
        this.id = id;
        this.img = img;
        this.img2 = img2;
        this.name = name;
        this.content = content;
        this.reach = reach;
        this.interactions = interactions;
        this.interactionRate = interactionRate;
        this.nameEN = nameEN;
        this.contentEN = contentEN;
    }
}
