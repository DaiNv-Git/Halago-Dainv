package com.example.halagodainv.response;

import lombok.Data;

@Data
public class CampaignUserResponse {
    private int id;
    private int userId;
    private String userName;
    private String phoneNumber;
    private String campaignName;
    private int campaignId;
    private String email;
}
