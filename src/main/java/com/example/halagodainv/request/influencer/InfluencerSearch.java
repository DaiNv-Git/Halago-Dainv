package com.example.halagodainv.request.influencer;

import com.example.halagodainv.request.SearchPageForm;
import lombok.Data;

@Data
public class InfluencerSearch extends SearchPageForm {
    private Boolean isFacebook = null;
    private Boolean isInstagram = null;
    private Boolean isTikTok = null;
    private Boolean isYoutube = null;
    private String industry = "";
    private int provinceId = 0;
    private String follower = "";
    private String expanse = "";
    private int sex = 0;
    private String birhYear = "";
}
