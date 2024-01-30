package com.example.halagodainv.service;

import com.example.halagodainv.dto.campain.CampaignRecruitment;
import com.example.halagodainv.request.campaign.CampaignAddRequest;
import com.example.halagodainv.request.campaign.CampaignEditRequest;
import com.example.halagodainv.request.campaign.CampaignFormSearch;
import com.example.halagodainv.response.CampaignUserResponse;
import com.example.halagodainv.response.PageResponse;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.util.List;

public interface CampaignService {
    Object getCampaigns(CampaignFormSearch campaignSearch);

    Object getDetail(int campaignId, String language);

    Object getDetailFull(int campaignId);

    Object add(CampaignAddRequest campaignAddRequest) throws ParseException;

    Object edit(CampaignEditRequest campaignEditRequest) throws ParseException;

    Object deleteByCampaign(int campaignId);

    Object getByBrands();

    Object getByIndustry();

    Object getCampaignCommunications();

    Object getCampaignStatuses();

    Object getCampaignCategories();

    Object getRelateToCampaigns(List<Integer> industryIds, int camId, int workStatus, String language);

    Object isCheckRecruitment(int idInflu, int idCampaign);

    PageResponse<CampaignRecruitment> getRecruitmentList(int campaignId, int pageSize, int pageNo, String language);
    PageResponse<CampaignUserResponse> getRecruitmentUserList(int campaignId,String userName,String language, int pageSize, int pageNo, Pageable pageable);

}
