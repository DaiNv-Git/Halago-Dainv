package com.example.halagodainv.service.impl;

import com.example.halagodainv.dto.hompage.*;
import com.example.halagodainv.exception.GeneralException;
import com.example.halagodainv.model.viewdisplayentity.HomepageEntitty;
import com.example.halagodainv.model.viewdisplayentity.PartnerEntity;
import com.example.halagodainv.repository.*;
import com.example.halagodainv.repository.viewdisplay.HomePageRepository;
import com.example.halagodainv.repository.viewdisplay.PartnerRepository;
import com.example.halagodainv.request.homepage.HomeUpdateRequest;
import com.example.halagodainv.request.homepage.PartnerRequest;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.service.HomePageService;
import com.example.halagodainv.until.FileImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class HomePageServiceImpl implements HomePageService {

    private final NewsRepository newsRepository;
    private final HomePageRepository homePageRepository;
    private final PartnerRepository partnerRepository;
    private final FileImageUtil fileImageUtil;
    public static String PARTNER = "partner";

    @Override
    public Object getHomePage(String language) throws GeneralException {
        try {
            Pageable page = PageRequest.of(0, 10);
            List<NewsTenDto> newsTenDtos = newsRepository.getHomeLanguage(language, page);
            HomePageDto homePageDto = new HomePageDto(homePageRepository.findAll(), newsTenDtos);
            return new BaseResponse<>(HttpStatus.OK.value(), "success", homePageDto);
        } catch (Exception e) {
            throw new GeneralException(e.getLocalizedMessage());
        }
    }

    public Object getDetail() throws GeneralException {
        return null;
    }

    public Object updateHomePage(HomeUpdateRequest request) throws GeneralException {
        try {
            homePageRepository.deleteAll();
            HomepageEntitty homepageEntitty = new HomepageEntitty();
            homepageEntitty.setForeignBrands(request.getForeignBrands());
            homepageEntitty.setDomesticBrands(request.getDomesticBrands());
            homepageEntitty.setSuccessfulCampaign(request.getSuccessfulCampaign());
            homepageEntitty.setKOLsInformational(request.getKOLsInformational());
            homepageEntitty = homePageRepository.save(homepageEntitty);
            return new BaseResponse<>(HttpStatus.OK.value(), "edit success", homepageEntitty);
        } catch (Exception e) {
            throw new GeneralException(e.getLocalizedMessage());
        }
    }

    public Object getPartner(int partnerID) throws GeneralException {
        try {
            return new BaseResponse<>(HttpStatus.OK.value(), "success", partnerRepository.findByPartnerId(partnerID));
        } catch (Exception e) {
            throw new GeneralException(e.getLocalizedMessage());
        }
    }

    public Object updateLogo(List<PartnerRequest> partnerRequests) {
        List<PartnerEntity> partnerEntities = new ArrayList<>();
        partnerRepository.deleteByPartnerId(partnerRequests.get(0).getPartnerId());
        for (PartnerRequest partnerRequest : partnerRequests) {
            PartnerEntity partnerNew = new PartnerEntity();
            partnerNew.setLogo(fileImageUtil.uploadImage(partnerRequest.getLogo()));
            partnerNew.setPartnerId(partnerRequests.get(0).getPartnerId());
            partnerEntities.add(partnerNew);
        }
        partnerRepository.saveAll(partnerEntities);
        return new BaseResponse<>(HttpStatus.OK.value(), "success", partnerEntities);
    }

    public void deleteLogo(Long id) {
        partnerRepository.deleteById(id);
    }
}
