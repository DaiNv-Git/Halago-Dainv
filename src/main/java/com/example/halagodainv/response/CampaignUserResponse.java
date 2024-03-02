package com.example.halagodainv.response;

import lombok.Data;

@Data
public class CampaignUserResponse {
    private int id;
    private Integer userId;
    private Integer age;
    private String field;
    private String userName;
    private String phoneNumber;
    private String campaignName;
    private Integer campaignId;
    private String email;
}
