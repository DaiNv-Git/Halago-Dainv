package com.example.halagodainv.dto.kol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KolCelebDto {
    private String title;
    private String imageKol1;
    private String imageKol2;
    private String approach;
    private String interact;
    private String ratioInteract;
    private String introduce;
    private String introduceDetail;
    List<BestPickDto> bestPickDtos = new ArrayList<>();

}
