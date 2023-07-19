package com.example.halagodainv.service;


import com.example.halagodainv.request.Influencer.InfluencerAddRequest;
import com.example.halagodainv.request.Influencer.InfluencerSearch;
import com.example.halagodainv.response.Influencer.InfluencerResponse;

public interface InfluencerService {
    Object getDetail(InfluencerSearch search);
    InfluencerResponse findInfluencerById(int id);
    Object add(InfluencerAddRequest request);
    Object delete(Integer id);
}
