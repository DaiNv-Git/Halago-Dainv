package com.example.halagodainv.dto.campain;

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
    private int id;
    private int brandId;
    private String campaignName;
    private int industryId;
    private String startDate;
    private String endDate;
    private String campaignImage;
    private String titleCampaign;
    private List<ImageProductDto> imageProductAddRequests = new ArrayList<>();
    private String titleProduct;
    private String descriptionCampaign;
    private String descriptionCandidatePerform;
    private String reward;

    public CampaignDto(CampaignEntity campaignEntity, List<ImageProductEntity> response) {
        this.id = campaignEntity.getId();
        this.brandId = campaignEntity.getIdBrand();
        this.campaignName = campaignEntity.getCampaignName();
        this.industryId = campaignEntity.getIndustryId();
        this.startDate = DateUtilFormat.convertDateToString(campaignEntity.getDateStart(), "yyyy-MM-dd");
        this.endDate = DateUtilFormat.convertDateToString(campaignEntity.getDateEnd(), "yyyy-MM-dd");
        this.campaignImage = campaignEntity.getImg();
        this.titleCampaign = campaignEntity.getTitleCampaign();
        this.titleProduct = campaignEntity.getTitleProduct();
        this.descriptionCampaign = campaignEntity.getContent();
        this.descriptionCandidatePerform = campaignEntity.getDescription();
        this.reward = campaignEntity.getRewards();
        response.forEach(campaign -> {
            this.imageProductAddRequests.add(new ImageProductDto(campaign));
        });
    }
}
