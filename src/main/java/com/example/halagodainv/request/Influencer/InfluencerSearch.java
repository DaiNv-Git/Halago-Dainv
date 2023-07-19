package com.example.halagodainv.request.Influencer;

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
}
