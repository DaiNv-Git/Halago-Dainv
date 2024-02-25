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
public class CampaignDetailDto {
    private int id;
    private String img;
    private String brandName;
    private String campaignName;
    private List<Integer> campaignCategory = new ArrayList<>();
    private List<Integer> campaignCommunication = new ArrayList<>();
    private List<Integer> industryId = new ArrayList<>();
    private int workStatus;
    private String conditionApply;
    private String method;
    private String hashtag;
    private String outstandingProduct;
    private String content;
    private String other;
    private String timeDeadline;
    private String thumbnail1;
    private String thumbnail2;
    private String thumbnail3;

    public CampaignDetailDto(CampaignEntity campaignEntity,String language) {
        this.id = campaignEntity.getId();
        this.img = campaignEntity.getImg();
        this.workStatus = campaignEntity.getWorkStatus();
        if (language.equals("vn")){
            this.campaignName = campaignEntity.getCampaignName();
            this.conditionApply = campaignEntity.getConditionApply();
            this.method = campaignEntity.getMethod();
            this.outstandingProduct = campaignEntity.getOutstandingProduct();
            this.content = campaignEntity.getContent();
            this.other = campaignEntity.getOther();
        }else if (language.equals("en")){
            this.campaignName = campaignEntity.getCampaignNameEN();
            this.conditionApply = campaignEntity.getConditionApplyEN();
            this.method = campaignEntity.getMethodEN();
            this.outstandingProduct = campaignEntity.getOutstandingProductEN();
            this.content = campaignEntity.getContentEN();
            this.other = campaignEntity.getOtherEN();
        }

        this.hashtag = campaignEntity.getHashtag();
        this.timeDeadline = campaignEntity.getTimeDeadline();
        this.campaignCategory = InfluencerServiceImpl.parseStringToListOfIntegers(campaignEntity.getCampaignCategory());
        this.campaignCommunication = InfluencerServiceImpl.parseStringToListOfIntegers(campaignEntity.getCampaignCommunication());
        this.industryId = InfluencerServiceImpl.parseStringToListOfIntegers(campaignEntity.getIndustryId());
        this.brandName = campaignEntity.getBrandName();
        this.thumbnail1 = campaignEntity.getImage1();
        this.thumbnail2 = campaignEntity.getImage2();
        this.thumbnail3 = campaignEntity.getImage3();
    }
}
