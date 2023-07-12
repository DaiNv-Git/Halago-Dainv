package com.example.halagodainv.service.impl;


import com.example.halagodainv.dto.CampaignDto;
import com.example.halagodainv.exception.ErrorResponse;
import com.example.halagodainv.model.CampaignEntity;
import com.example.halagodainv.repository.CampaignRepository;
import com.example.halagodainv.request.campaign.CampaignAddRequest;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.service.CampaignService;
import com.example.halagodainv.until.DateUtilFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignServiceImpl implements CampaignService {
    private final CampaignRepository campaignRepository;

    public Object add(CampaignAddRequest campaignAddRequest) throws ParseException {
        List<ErrorResponse> errorResponses = new ArrayList<>();
        if (!campaignAddRequest.validate(errorResponses)) {
            return errorResponses;
        }
        CampaignEntity campaignEntity = new CampaignEntity();
        campaignEntity.setBrandName(campaignAddRequest.getBrandName());
        campaignEntity.setCampaignName(campaignAddRequest.getCampaignName());
        campaignEntity.setIndustry(campaignAddRequest.getIndustry());
        campaignEntity.setDateStart(DateUtilFormat.converStringToDate(campaignAddRequest.getStartDate(), "dd-MM-yyyy"));
        campaignEntity.setDateEnd(DateUtilFormat.converStringToDate(campaignAddRequest.getEndDate(), "dd-MM-yyyy"));
        campaignEntity.setImg(campaignAddRequest.getCampaignImage());
        campaignEntity.setImgProduct(campaignAddRequest.getProductImage());
        campaignEntity.setTitleCampaign(campaignAddRequest.getTitleCampaign());
        campaignEntity.setTitleProduct(campaignAddRequest.getTitleProduct());
        campaignEntity.setDescription(campaignAddRequest.getDescriptionCampaign());
        campaignEntity.setContent(campaignAddRequest.getDescriptionCandidatePerform());
        campaignEntity.setRewards(campaignAddRequest.getReward());
        campaignEntity = campaignRepository.save(campaignEntity);
        return new BaseResponse<>(HttpStatus.CREATED.value(), "add success", new CampaignDto(campaignEntity));
    }
}
