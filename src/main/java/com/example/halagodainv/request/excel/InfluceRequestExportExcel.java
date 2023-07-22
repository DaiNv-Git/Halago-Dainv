package com.example.halagodainv.request.excel;

import lombok.Data;

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
}
