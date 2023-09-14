package com.example.halagodainv.request.campaign;

import com.example.halagodainv.exception.ErrorResponse;
import com.example.halagodainv.request.campaign.imageproduct.ImageProductAddRequest;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class CampaignAddRequest {
    private int brandId;
    private String campaignName;
    private List<Integer> industryId;
    private String campaignImage;
    private String titleCampaign;
    private String startDate;
    private String endDate;
    private List<ImageProductAddRequest> imageProductAddRequests;
    private String titleProduct;
    private String descriptionCampaign;
    private String descriptionCandidatePerform;
    private String reward;

    public boolean validate(List<ErrorResponse> response) {
        boolean isCheck = true;
        if (StringUtils.isBlank(campaignName)) {
            response.add(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "campaign name không được rỗng", null));
            isCheck = false;
        }
        if (StringUtils.isBlank(titleCampaign)) {
            response.add(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "titleCampaign không được rỗng", null));
            isCheck = false;
        }
        if (StringUtils.isBlank(titleProduct)) {
            response.add(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "titleProduct không được rỗng", null));
            isCheck = false;
        }
        if (StringUtils.isBlank(descriptionCampaign)) {
            response.add(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "descriptionCampaign không được rỗng", null));
            isCheck = false;
        }
        if (StringUtils.isBlank(descriptionCandidatePerform)) {
            response.add(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "descriptionCandidatePerform không được rỗng", null));
            isCheck = false;
        }
        if (StringUtils.isBlank(reward)) {
            response.add(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "reward không được rỗng", null));
            isCheck = false;
        }
        return isCheck;
    }
}
