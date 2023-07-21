package com.example.halagodainv.dto.influcer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InflucerDtoListDetail {
    private long id;
    private String name;
    private boolean isFacebook;
    private boolean isTikTok;
    private boolean isInstagram;
    private boolean isYouTube;
    private String industry;
    private String phone;
    private int sex;
    private String birtYear;
    private int classify;
    private int provinceId;
    private String address;
    private int bankId;
    private String bankNumber;
    private String follower;
    private String expense;
    private String link;
    private String channel;
    private Date createHistory;
    private String email;
}
