package com.example.halagodainv.service;


import com.example.halagodainv.request.influencer.InFluencerSubMenuSearch;
import com.example.halagodainv.request.influencer.InfluencerAddRequest;
import com.example.halagodainv.request.influencer.InfluencerSearch;
import com.example.halagodainv.response.Influencer.InfluencerResponse;

public interface InfluencerService {
    Object getInfluMenu(InfluencerSearch search);

    Object getInfluSubMenu(InFluencerSubMenuSearch search);

    Object findInfluencerById(long id);

    Object add(InfluencerAddRequest request);
    Object edit(InfluencerAddRequest request);

    Object delete(long id);
}
