package com.example.halagodainv.request.foot;

import lombok.Data;

@Data
public class FootEditRequest {
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
}
