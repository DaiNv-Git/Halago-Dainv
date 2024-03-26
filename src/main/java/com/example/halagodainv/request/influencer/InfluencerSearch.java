package com.example.halagodainv.request.influencer;

import com.example.halagodainv.request.SearchPageForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfluencerSearch extends SearchPageForm {
    private String name = "";
    private Long id;
    private String phoneNumber;
    private Boolean isFacebook = null;
    private Boolean isInstagram = null;
    private Boolean isTikTok = null;
    private Boolean isYoutube = null;
    private String industry = "";
    private String provinceId = "";
    private String startFollower = "";
    private String endFollower = "";
    private String startExpanse = "";
    private String endExpanse = "";
    private String sex = "";
    private String startYear = "";
    private String endYear = "";
    private int ageStart = 0;
    private int ageEnd = 100;
    private List<Integer> ids = new ArrayList<>();
}
