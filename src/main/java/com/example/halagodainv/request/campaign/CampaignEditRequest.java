package com.example.halagodainv.request.campaign;

import com.example.halagodainv.exception.ErrorResponse;
import com.example.halagodainv.request.campaign.imageproduct.ImageProductEditRequest;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Data
public class CampaignEditRequest {
    private int id;
    private int brandId;
    private String campaignName;
    private List<Integer> industryId = new ArrayList<>();
    private String campaignImage;
    private String titleCampaign;
    private String startDate = "1900";
    private String endDate = "9999";
    private List<ImageProductEditRequest> imageProductAddRequests = new ArrayList<>();
    private String titleProduct;
    private String descriptionCampaign;
    private String descriptionCandidatePerform;
    private String reward;
    private List<Integer> campaignCommunication = new ArrayList<>();
    private int campaignStatus;
    private List<Integer> campaignCategory = new ArrayList<>();

    public boolean validate(List<ErrorResponse> response) {
        boolean isCheck = true;
        if (StringUtils.isBlank(campaignName)) {
            response.add(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "campaign name is not null or empty", null));
            isCheck = false;
        }
        if (StringUtils.isBlank(titleCampaign)) {
            response.add(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "titleCampaign is not null or empty", null));
            isCheck = false;
        }
        if (StringUtils.isBlank(titleProduct)) {
            response.add(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "titleProduct is not null or empty", null));
            isCheck = false;
        }
        if (StringUtils.isBlank(descriptionCampaign)) {
            response.add(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "descriptionCampaign is not null or empty", null));
            isCheck = false;
        }
        if (StringUtils.isBlank(descriptionCandidatePerform)) {
            response.add(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "descriptionCandidatePerform is not null or empty", null));
            isCheck = false;
        }
        if (StringUtils.isBlank(reward)) {
            response.add(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "reward is not null or empty", null));
            isCheck = false;
        }
        return isCheck;
    }
}
