package com.example.halagodainv.dto.campain;

import com.example.halagodainv.model.campaign.CampaignEntity;
import com.example.halagodainv.service.impl.InfluencerServiceImpl;
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
    private int workStatus;
    private List<Integer> campaignCategory = new ArrayList<>();
    private String timeDeadline;
    private List<Integer> campaignCommunication = new ArrayList<>();


    public CampaignDto(CampaignEntity campaignEntity) {
        this.id = campaignEntity.getId();
        this.img = campaignEntity.getImg();
        this.campaignName = campaignEntity.getCampaignName();
        this.workStatus = campaignEntity.getCampaignStatus();
        this.timeDeadline = campaignEntity.getTimeDeadline();
        this.campaignCategory = InfluencerServiceImpl.parseStringToListOfIntegers(campaignEntity.getCampaignCategory());
        this.campaignCommunication = InfluencerServiceImpl.parseStringToListOfIntegers(campaignEntity.getCampaignCommunication());
    }
}
