package com.example.halagodainv.request.influencer;

import lombok.Data;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@Data
public class InfluencerAddRequest {
    private long Id;
    private String name;
    private String phone;
    private Integer sex ;
    private String birtYear;
    private Boolean isFacebook = null;
    private Boolean isTikTok = null;
    private Boolean isYoutube = null;
    private Boolean isInstagram = null;
    private String email;
    private List<Integer> classifyId;
    private Integer provinceId;
    private List<Integer> industry;
    private String bankId ="";
    private String bankNumber;
    private String address;
    private String linkFb;
    private String followerFb;
    private String expenseFb;
    private String linkTT;
    private String followerTT;
    private String expenseTT;
    private String linkYT;
    private String followerYT;
    private String expenseYT;
    private String linkIns;
    private String followerIns;
    private String expenseIns;
}
