package com.example.halagodainv.service;

import com.example.halagodainv.exception.GeneralException;
import com.example.halagodainv.request.homepage.HomePageRequest;

public interface HomePageService {
    Object getHomePage(String language) throws GeneralException;

    Object getPartner(int partnerID) throws GeneralException;

    Object getDetail() throws GeneralException;

    Object updateHomePage(Long id, String follow) throws GeneralException;
}
