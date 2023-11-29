package com.example.halagodainv.service;

import com.example.halagodainv.request.campaign.CampaignAddRequest;
import com.example.halagodainv.request.campaign.CampaignEditRequest;
import com.example.halagodainv.request.campaign.CampaignFormSearch;

import java.text.ParseException;

public interface CampaignService {
    Object getCampaigns(CampaignFormSearch campaignSearch);

    Object getDetail(int campaignId);

    Object add(CampaignAddRequest campaignAddRequest) throws ParseException;

    Object edit(CampaignEditRequest campaignEditRequest) throws ParseException;

    Object deleteByCampaign(int campaignId);
    Object getByBrands();
    Object getByIndustry();

    public Object getCampaignCommunications();
    public Object getCampaignStatuses();
    public Object getCampaignCategories();


}
