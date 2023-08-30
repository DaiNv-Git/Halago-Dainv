package com.example.halagodainv.dto.influcer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class InflucerDtoListDetail {
    private long id;
    private String name;
    private Boolean isFacebook;
    private Boolean isTikTok;
    private Boolean isInstagram;
    private Boolean isYouTube;
    private String industry;
    private String phone;
    private int sex;
    private String birtYear;
    private String classify;
    private int provinceId;
    private String address;
    private String bankId;
    private String bankNumber;
    private String follower;
    private String expense;
    private String link;
    private String channel;
    private Date createHistory;
    private String email;

    public InflucerDtoListDetail(long id, String name, Boolean isFacebook, Boolean isTikTok, Boolean isInstagram, Boolean isYouTube, String industry, String phone, int sex, String birtYear, String classify, int provinceId, String address, String bankId, String bankNumber, String follower, String expense, String link, String channel, Date createHistory, String email) {
        this.id = id;
        this.name = name == null ? "" : name;
        this.isFacebook = isFacebook;
        this.isTikTok = isTikTok;
        this.isInstagram = isInstagram;
        this.isYouTube = isYouTube;
        this.industry = industry;
        this.phone = phone;
        this.sex = sex;
        this.birtYear = birtYear;
        this.classify = classify;
        this.provinceId = provinceId;
        this.address = address;
        this.bankId = bankId != null ? bankId : "";
        this.bankNumber = bankNumber;
        this.follower = follower;
        this.expense = expense;
        this.link = link;
        this.channel = channel;
        this.createHistory = createHistory;
        this.email = email;
    }
}
