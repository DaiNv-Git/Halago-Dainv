package com.example.halagodainv.dto.campain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CampaignDetailDto {
    private int id;
    private int brandId;
    private String brandName;
    private String campaignName;
    private int industryId;
    private String industryName;
    private String startDate;
    private String endDate;
    private String campaignImage;
    private String titleCampaign;
    private List<ImageProductDto> imageProductAddRequests = new ArrayList<>();
    private String titleProduct;
    private String descriptionCampaign;
    private String descriptionCandidatePerform;
    private String reward;
}
