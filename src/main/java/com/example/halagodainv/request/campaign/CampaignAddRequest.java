package com.example.halagodainv.request.campaign;

import com.example.halagodainv.request.campaign.imageproduct.ImageProductAddRequest;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CampaignAddRequest {
    private String braneName;
    private String campaignName;
    private List<Integer> industryId = new ArrayList<>();
    private String campaignImage;
    private String titleCampaign;
    private List<ImageProductAddRequest> imageProductAddRequests = new ArrayList<>();
    private String titleProduct;
    private String descriptionCampaign;
    private String descriptionCandidatePerform;
    private List<Integer> campaignCommunication = new ArrayList<>();
    private int campaignStatus;
    private List<Integer> campaignCategory = new ArrayList<>();
}
