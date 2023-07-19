package com.example.halagodainv.request.campaign;

import com.example.halagodainv.exception.ErrorResponse;
import com.example.halagodainv.request.campaign.imageproduct.ImageProductEditRequest;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;

import java.util.List;
@Data
public class CampaignEditRequest {
    private int id;
    private int brandId;
    private String campaignName;
    private int industryId;
    private String campaignImage;
    private String titleCampaign;
    private String startDate;
    private String endDate;
    private List<ImageProductEditRequest> imageProductAddRequests;
    private String titleProduct;
    private String descriptionCampaign;
    private String descriptionCandidatePerform;
    private String reward;

    public boolean validate(List<ErrorResponse> response) {
        boolean isCheck = true;
        if (Strings.isBlank(campaignName)) {
            response.add(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "campaign name is not null or empty", null));
            isCheck = false;
        }
        if (Strings.isBlank(titleCampaign)) {
            response.add(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "titleCampaign is not null or empty", null));
            isCheck = false;
        }
        if (Strings.isBlank(titleProduct)) {
            response.add(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "titleProduct is not null or empty", null));
            isCheck = false;
        }
        if (Strings.isBlank(descriptionCampaign)) {
            response.add(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "descriptionCampaign is not null or empty", null));
            isCheck = false;
        }
        if (Strings.isBlank(descriptionCandidatePerform)) {
            response.add(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "descriptionCandidatePerform is not null or empty", null));
            isCheck = false;
        }
        if (Strings.isBlank(reward)) {
            response.add(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "reward is not null or empty", null));
            isCheck = false;
        }
        return isCheck;
    }
}
