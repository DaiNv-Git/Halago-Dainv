package com.example.halagodainv.service;


import com.example.halagodainv.request.Influencer.InfluencerAddRequest;
import com.example.halagodainv.request.Influencer.InfluencerSearchRequest;
import com.example.halagodainv.response.Influencer.InfluencerResponse;
import com.example.halagodainv.response.Influencer.InfluencerSearchDTO;
import com.example.halagodainv.response.PageResponse;

public interface InfluencerService {
    InfluencerResponse findInfluencerById(int id);
    Object add(InfluencerAddRequest request);
    Object delete(Integer id);
    PageResponse<InfluencerSearchDTO> searchInfluencers(InfluencerSearchRequest request);
}
