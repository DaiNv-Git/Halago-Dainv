package com.example.halagodainv.dto.campain;

import com.example.halagodainv.model.campaign.CampaignEntity;
import com.example.halagodainv.service.impl.InfluencerServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CampaignDetailFullDto {
    private int id;
    private String img;
    private String brandName;
    private String campaignName;
    private String campaignNameEN;
    private List<Integer> campaignCategory = new ArrayList<>();
    private List<Integer> campaignCommunication = new ArrayList<>();
    private int workStatus;
    private String conditionApply;
    private String conditionApplyEN;
    private String method;
    private String methodEN;
    private String hashtag;
    private String outstandingProduct;
    private String outstandingProductEN;
    private String content;
    private String contentEN;
    private String other;
    private String otherEN;
    private String timeDeadline;
    private String thumbnail1;
    private String thumbnail2;
    private String thumbnail3;

    public CampaignDetailFullDto(CampaignEntity campaignEntity) {
        this.id = campaignEntity.getId();
        this.img = campaignEntity.getImg();
        this.workStatus = campaignEntity.getWorkStatus();
        this.campaignName = campaignEntity.getCampaignName();
        this.conditionApply = campaignEntity.getConditionApply();
        this.method = campaignEntity.getMethod();
        this.outstandingProduct = campaignEntity.getOutstandingProduct();
        this.content = campaignEntity.getContent();
        this.other = campaignEntity.getOther();
        this.campaignNameEN = campaignEntity.getCampaignNameEN();
        this.conditionApplyEN = campaignEntity.getConditionApplyEN();
        this.methodEN = campaignEntity.getMethodEN();
        this.outstandingProductEN = campaignEntity.getOutstandingProductEN();
        this.contentEN = campaignEntity.getContentEN();
        this.otherEN = campaignEntity.getOtherEN();
        this.hashtag = campaignEntity.getHashtag();
        this.timeDeadline = campaignEntity.getTimeDeadline();
        this.campaignCategory = InfluencerServiceImpl.parseStringToListOfIntegers(campaignEntity.getCampaignCategory());
        this.campaignCommunication = InfluencerServiceImpl.parseStringToListOfIntegers(campaignEntity.getCampaignCommunication());
        this.brandName = campaignEntity.getBrandName();
        this.thumbnail1 = campaignEntity.getImage1();
        this.thumbnail2 = campaignEntity.getImage2();
        this.thumbnail3 = campaignEntity.getImage3();
    }
}
