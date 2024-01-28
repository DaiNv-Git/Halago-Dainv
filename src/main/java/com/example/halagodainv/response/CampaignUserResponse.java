package com.example.halagodainv.response;

import lombok.Data;

@Data
public class CampaignUserResponse {
    private int id;
    private String userName;
    private String phoneNumber;
    private String campaignName;
    private String email;
}
