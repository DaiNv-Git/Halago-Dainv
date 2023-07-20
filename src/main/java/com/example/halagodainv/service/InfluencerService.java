package com.example.halagodainv.service;


import com.example.halagodainv.request.influencer.InfluencerAddRequest;
import com.example.halagodainv.request.Influencer.InfluencerSearch;
import com.example.halagodainv.response.Influencer.InfluencerResponse;

public interface InfluencerService {
    Object getInfluMenu(InfluencerSearch search);
    InfluencerResponse findInfluencerById(int id);
    Object add(InfluencerAddRequest request);
    Object delete(Integer id);
}
