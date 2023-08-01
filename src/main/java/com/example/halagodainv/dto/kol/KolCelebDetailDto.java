package com.example.halagodainv.dto.kol;

import com.example.halagodainv.model.BestKolImageEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class KolCelebDetailDto {
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
    private List<BestKolImageEntity> kolImageEntityList = new ArrayList<>();

    public KolCelebDetailDto(KolCelMapEntity map, List<BestKolImageEntity> kolImageEntityList) {
        this.id = map.getId();
        this.title = map.getTitle();
        this.titleEn = map.getTitleEn();
        this.imageKol1 = map.getImageKol1();
        this.imageKol2 = map.getImageKol2();
        this.approach = map.getApproach();
        this.interact = map.getInteract();
        this.ratioInteract = map.getRatioInteract();
        this.introduce = map.getIntroduce();
        this.introduceDetail = map.getIntroduceDetail();
        this.introduceEN = map.getIntroduceEN();
        this.introduceDetailEN = map.getIntroduceDetailEN();
        this.kolImageEntityList = kolImageEntityList;
    }
}
