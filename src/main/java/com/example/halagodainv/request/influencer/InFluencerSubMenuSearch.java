package com.example.halagodainv.request.influencer;

import com.example.halagodainv.request.SearchPageForm;
import lombok.Data;

@Data
public class InFluencerSubMenuSearch extends SearchPageForm {
    private Boolean isFacebook = null;
    private Boolean isTikTok = null;
    private Boolean isYoutube = null;
    private Boolean isInstagram = null;
    private String follower;
    private String expanse;
    private String industry;
}
