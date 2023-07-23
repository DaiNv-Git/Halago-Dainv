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
    private String provinceId = "";
    private String follower = "";
    private String expanse = "";
    private String sex = "";
    private String birhYear = "";
}
