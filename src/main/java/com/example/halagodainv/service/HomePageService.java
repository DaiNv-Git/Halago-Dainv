package com.example.halagodainv.service;

import com.example.halagodainv.exception.GeneralException;

public interface HomePageService {
    Object getHomePage(String language) throws GeneralException;

    Object getPartner(int partnerID) throws GeneralException;
}
