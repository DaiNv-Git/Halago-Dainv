package com.example.halagodainv.dto.campain;

import com.example.halagodainv.model.campaign.CampaignEntity;
import com.example.halagodainv.model.ImageProductEntity;
import com.example.halagodainv.service.impl.InfluencerServiceImpl;
import com.example.halagodainv.until.DateUtilFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignDto {
    private int id;
    private String img;
    private String campaignName;
    private String workStatus;
    private String categoryCampaign;
    private String timeDeadline;


    public CampaignDto(CampaignEntity campaignEntity) {
        this.id = campaignEntity.getId();
        this.img = campaignEntity.getImg();
        this.campaignName = campaignEntity.getCampaignName();
        this.workStatus = campaignEntity.getCampaignCategory();
        this.timeDeadline = campaignEntity.getTimeDeadline();
        this.categoryCampaign = campaignEntity.getCampaignCategory();
    }
}
