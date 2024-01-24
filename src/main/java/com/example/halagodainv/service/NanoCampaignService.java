package com.example.halagodainv.service;

import com.example.halagodainv.model.viewdisplayentity.nanocampaign.NanoCampaignEntity;
import com.example.halagodainv.request.nanocampaign.NanoCampaignRequest;
import com.example.halagodainv.response.BaseResponse;

import java.util.List;

public interface NanoCampaignService {
    BaseResponse<List<NanoCampaignEntity>> getList();
    BaseResponse<List<NanoCampaignEntity>> updateNano(List<NanoCampaignRequest> nanoCampaignRequests);
}
