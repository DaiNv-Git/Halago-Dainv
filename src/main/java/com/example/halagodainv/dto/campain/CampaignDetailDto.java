package com.example.halagodainv.dto.campain;

import com.example.halagodainv.model.ImageProductEntity;
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
public class CampaignDetailDto {
    private int id;
    private String img;
    private String brandName;
    private String campaignName;
    private List<Integer> campaignCategory = new ArrayList<>();
    private List<Integer> campaignCommunication = new ArrayList<>();
    private int workStatus;
    private String conditionApply;
    private String method;
    private String hashtag;
    private String outstandingProduct;
    private String content;
    private String other;
    private String timeDeadline;
    private List<ImageProductEntity> imageProductAddRequests = new ArrayList<>();

    public CampaignDetailDto(CampaignEntity campaignEntity, List<ImageProductEntity> imageProductAddRequests) {
        this.id = campaignEntity.getId();
        this.img = campaignEntity.getImg();
        this.campaignName = campaignEntity.getCampaignName();
        this.workStatus = campaignEntity.getCampaignStatus();
        this.conditionApply = campaignEntity.getConditionApply();
        this.method = campaignEntity.getMethod();
        this.hashtag = campaignEntity.getHashtag();
        this.outstandingProduct = campaignEntity.getOutstandingProduct();
        this.content = campaignEntity.getContent();
        this.other = campaignEntity.getOther();
        this.timeDeadline = campaignEntity.getTimeDeadline();
        this.imageProductAddRequests = imageProductAddRequests;
        this.campaignCategory = InfluencerServiceImpl.parseStringToListOfIntegers(campaignEntity.getCampaignCategory());
        this.campaignCommunication = InfluencerServiceImpl.parseStringToListOfIntegers(campaignEntity.getCampaignCommunication());
        this.brandName = campaignEntity.getBrandName();
    }
}
