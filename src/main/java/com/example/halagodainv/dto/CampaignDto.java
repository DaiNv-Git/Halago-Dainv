package com.example.halagodainv.dto;

import com.example.halagodainv.model.CampaignEntity;
import com.example.halagodainv.model.ImageProductEntity;
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
    private String brandName;
    private String campaignName;
    private String industry;
    private String startDate;
    private String endDate;
    private String campaignImage;
    private String titleCampaign;
    private List<ImageProductEntity> productImage = new ArrayList<>();
    private String titleProduct;
    private String descriptionCampaign;
    private String descriptionCandidatePerform;
    private String reward;

    public CampaignDto(CampaignEntity campaignEntity) {
        this.brandName = campaignEntity.getBrandName();
        this.campaignName = campaignEntity.getCampaignName();
        this.industry = campaignEntity.getIndustry();
        this.startDate = DateUtilFormat.convertDateToString(campaignEntity.getDateStart(), "dd-MM-yyyy");
        this.endDate = DateUtilFormat.convertDateToString(campaignEntity.getDateEnd(), "dd-MM-yyyy");
        this.campaignImage = campaignEntity.getImg();
        this.titleCampaign = campaignEntity.getTitleCampaign();
        this.productImage.addAll(campaignEntity.getProductEntityList());
        this.titleProduct = campaignEntity.getTitleProduct();
        this.descriptionCampaign = campaignEntity.getContent();
        this.descriptionCandidatePerform = campaignEntity.getDescription();
        this.reward = campaignEntity.getRewards();
    }
}
