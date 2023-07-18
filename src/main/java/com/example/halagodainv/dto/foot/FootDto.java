package com.example.halagodainv.dto.foot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FootDto {
    private int id;
    private String companyVN;
    private String companyEN;
    private String addressVN;
    private String addressEN;
    private String email;
    private String hotline;
    private String facebook;
    private String zalo;
    private String youtube;
    private String tiktok;
    private String instagram;
    private String language;
}
