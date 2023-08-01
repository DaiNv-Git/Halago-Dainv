package com.example.halagodainv.request.kolCeleb;

import com.example.halagodainv.model.BestKolImageEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KolCelebRequest {
    private Long id;
    private String title;
    private String titleEn;
    private String imageKol1;
    private String imageKol2;
    private String approach;
    private String interact;
    private String ratioInteract;
    private String introduce;
    private String introduceDetail;
    private String introduceEN;
    private String introduceDetailEN;
    private List<BestKolImageEntity> kolImageEntityList;
}
