package com.example.halagodainv.request.excel;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InfluceRequestExportExcel {
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
    private int ageStart = 0;
    private int ageEnd = 100;
    private List<Long> listIds = new ArrayList<>();
}
