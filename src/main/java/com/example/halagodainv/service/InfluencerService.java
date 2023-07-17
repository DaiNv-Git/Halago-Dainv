package com.example.halagodainv.service;


import com.example.halagodainv.request.Influencer.InfluencerAddRequest;
import com.example.halagodainv.response.Influencer.InfluencerResponse;

public interface InfluencerService {
    InfluencerResponse findInfluencerById(int id);
    Object add(InfluencerAddRequest request);
    Object delete(Integer id);
}
