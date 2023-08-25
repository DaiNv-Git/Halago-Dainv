package com.example.halagodainv.service;

import com.example.halagodainv.exception.GeneralException;
import com.example.halagodainv.request.homepage.HomePageRequest;
import com.example.halagodainv.request.homepage.HomeUpdateRequest;
import com.example.halagodainv.request.homepage.PartnerRequest;

import java.util.List;

public interface HomePageService {
    Object getHomePage(String language) throws GeneralException;

    Object getPartner(int partnerID) throws GeneralException;

    Object getDetail() throws GeneralException;

    Object updateHomePage(HomeUpdateRequest requests) throws GeneralException;

    Object updateLogo(List<PartnerRequest> partnerRequests);

    void deleteLogo(Long id);

}
