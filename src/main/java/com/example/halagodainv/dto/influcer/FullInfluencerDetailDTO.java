package com.example.halagodainv.dto.influcer;

import lombok.Data;

import java.sql.Date;
@Data
public class FullInfluencerDetailDTO {
    private int userId;
    private String userName;
    private String password;
    private int roleId;
    private Date userCreated;
    private int userStatus;
    private String userEmail;
    private String userPhone;

    private String influencerName;
    private Date influencerHistoryCreated;
    private Date influencerCreated;
    private Integer influencerSex;
    private String influencerPhone;
    private String influencerYearOld;
    private boolean isFacebook;
    private boolean isTiktok;
    private boolean isInstagram;
    private boolean isYoutube;
    private String influencerEmail;
    private String influencerIndustry;
    private String influencerIndustryName;
    private String influencerClassifyId;
    private String influencerClassifyName;
    private Integer influencerProvinceId;
    private String influencerAddress;
    private String influencerBankId;
    private String influencerAccountNumber;
    private Integer influencerAge;

    private long influencerDetailId;
    private long influencerDetailInfluId;
    private String influencerDetailFollower;
    private String influencerDetailExpense;
    private String influencerDetailUrl;
    private String influencerDetailChannel;
}
