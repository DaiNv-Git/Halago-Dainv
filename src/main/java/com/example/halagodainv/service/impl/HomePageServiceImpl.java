package com.example.halagodainv.service.impl;

import com.example.halagodainv.dto.hompage.*;
import com.example.halagodainv.dto.viewnews.ViewNewsMap;
import com.example.halagodainv.exception.GeneralException;
import com.example.halagodainv.model.AdvantageEntity;
import com.example.halagodainv.model.AdvantageEntityLanguage;
import com.example.halagodainv.model.EfficiencyOptimizationEntity;
import com.example.halagodainv.model.viewdisplayentity.HompageEntitty;
import com.example.halagodainv.repository.*;
import com.example.halagodainv.repository.viewdisplay.HomePageRepository;
import com.example.halagodainv.request.homepage.AdvantageRequest;
import com.example.halagodainv.request.homepage.HomePageRequest;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.service.HomePageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;

@Service
@RequiredArgsConstructor
public class HomePageServiceImpl implements HomePageService {

    private final NewsRepository newsRepository;
    private final HomePageRepository homePageRepository;
    private final BrandRepository brandRepository;

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

    public Object updateHomePage(Long id, String follow) throws GeneralException {
        try {
            Optional<HompageEntitty> hompageEntitty = homePageRepository.findById(id);
            if (hompageEntitty.isPresent()) {
                hompageEntitty.get().setFollow(follow);
                homePageRepository.save(hompageEntitty.get());
            }
            return new BaseResponse<>(HttpStatus.OK.value(), "success", hompageEntitty.get());
        } catch (Exception e) {
            throw new GeneralException(e.getLocalizedMessage());
        }
    }

    public Object getPartner(int partnerID) throws GeneralException {
        try {
            return new BaseResponse<>(HttpStatus.OK.value(), "success", brandRepository.getByLogo(String.valueOf(partnerID)));
        } catch (Exception e) {
            throw new GeneralException(e.getLocalizedMessage());
        }
    }
}
