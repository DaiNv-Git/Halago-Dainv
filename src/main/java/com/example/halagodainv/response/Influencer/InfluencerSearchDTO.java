package com.example.halagodainv.response.Influencer;

import lombok.Data;

@Data
public class InfluencerSearchDTO {
    private int id;
    private String name;
    private String description;
    private String industries;
    private Boolean fb;
    private Boolean youtobe;

    private Boolean titok;
    private Boolean instagram;
    private String phone;
    private String sexValue;

    private Double expenseValue;

    public InfluencerSearchDTO(int id, String name, String description, String industries, Boolean fb, Boolean youtobe, Boolean titok, Boolean instagram, String phone) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.industries = industries;
        this.fb = fb;
        this.youtobe = youtobe;
        this.titok = titok;
        this.instagram = instagram;
        this.phone = phone;
    }

    public InfluencerSearchDTO(int id, String name, String description, String industries, Boolean fb, Boolean youtobe, Boolean titok, Boolean instagram, String phone, String sexValue, Double expenseValue) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.industries = industries;
        this.fb = fb;
        this.youtobe = youtobe;
        this.titok = titok;
        this.instagram = instagram;
        this.phone = phone;
        this.sexValue = sexValue;
        this.expenseValue = expenseValue;
    }
}
