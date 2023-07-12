package com.example.halagodainv.service;

import com.example.halagodainv.request.campaign.CampaignAddRequest;
import com.example.halagodainv.request.campaign.CampaignEditRequest;
import com.example.halagodainv.request.campaign.CampaignSearch;

import java.text.ParseException;

public interface CampaignService {
    Object getCampaigns(CampaignSearch campaignSearch);
    Object add(CampaignAddRequest campaignAddRequest) throws ParseException;
    Object edit(CampaignEditRequest campaignEditRequest) throws ParseException;
    Object deleteByCampaign(int campaignId);


}
