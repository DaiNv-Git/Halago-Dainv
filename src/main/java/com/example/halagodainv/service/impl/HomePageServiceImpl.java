package com.example.halagodainv.service.impl;

import com.example.halagodainv.dto.hompage.*;
import com.example.halagodainv.exception.GeneralException;
import com.example.halagodainv.model.AdvantageEntity;
import com.example.halagodainv.model.EfficiencyOptimizationEntity;
import com.example.halagodainv.repository.AdvantageRepository;
import com.example.halagodainv.repository.BrandRepository;
import com.example.halagodainv.repository.EfficiencyOptimizationRepository;
import com.example.halagodainv.repository.NewsRepository;
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

    private final AdvantageRepository advantageRepository;
    private final EfficiencyOptimizationRepository efficiencyOptimizationRepository;
    private final NewsRepository newsRepository;
    private final BrandRepository brandRepository;

    @Override
    public Object getHomePage(String language) throws GeneralException {
        try {
            OriginativeDto originative = efficiencyOptimizationRepository.getOriginativeLanguage(language);
            List<AdvantageDto> advantageDtos = advantageRepository.getAdvantageLanguage(language);
            EfficiencyOptimizationDto efficiencyOptimization = efficiencyOptimizationRepository.getEfficiencyOptiLanguage(language);
            Pageable page = PageRequest.of(0, 10);
            List<NewsTenDto> newsTenDtos = newsRepository.getHomeLanguage(language, page);
            HomePageDto homePageDto = new HomePageDto(originative, advantageDtos, efficiencyOptimization, newsTenDtos);
            return new BaseResponse<>(HttpStatus.OK.value(), "success", homePageDto);
        } catch (Exception e) {
            throw new GeneralException(e.getLocalizedMessage());
        }
    }

    public Object getDetail() throws GeneralException {
        try {
            List<EfficiencyOptimizationEntity> efficiencyVN = efficiencyOptimizationRepository.getEfficiencyOptiLanguage();
            List<EfficiencyOptimizationEntity> Creatives = efficiencyOptimizationRepository.getOriginativeLanguage();
            List<AdvantageEntity> advantageEntities = advantageRepository.getAdvantageAll();
            List<AdvantageRequest> advantageRequests = new ArrayList<>();
            advantageEntities.forEach(i -> {
                if (i.getLanguage().equals("VN")) {
                    AdvantageRequest advantage = new AdvantageRequest();
                    advantage.setTitleVN(i.getTitle());
                    advantage.setContentVN(i.getContent());
                    advantageEntities.forEach(z -> {
                        if (i.getId() == (z.getId() - 2) && z.getLanguage().equals("EN")) {
                            advantage.setTitleEN(z.getTitle());
                            advantage.setContentEN(z.getContent());
                        }
                    });
                    advantageRequests.add(advantage);
                }
            });
            HomePageDetail homePageDetail = new HomePageDetail(efficiencyVN, Creatives, advantageRequests);
            return new BaseResponse<>(HttpStatus.OK.value(), "success", homePageDetail);
        } catch (Exception e) {
            throw new GeneralException(e.getLocalizedMessage());
        }
    }

    public Object updateHomePage(HomePageRequest homePageRequest) {
        List<EfficiencyOptimizationEntity> efficiencyOptimizationEntities = new ArrayList<>();
        Optional<EfficiencyOptimizationEntity> efficiencyVN = efficiencyOptimizationRepository.findById(1L);
        if (efficiencyVN.isPresent()) {
            efficiencyVN.get().setTitle(homePageRequest.getTitleEfficiencyOptimizationsVN());
            efficiencyVN.get().setContent(homePageRequest.getContentEfficiencyOptimizationsVN());
            efficiencyOptimizationEntities.add(efficiencyVN.get());
        }
        Optional<EfficiencyOptimizationEntity> efficiencyEN = efficiencyOptimizationRepository.findById(2L);
        if (efficiencyEN.isPresent()) {
            efficiencyEN.get().setTitle(homePageRequest.getTitleEfficiencyOptimizationsEN());
            efficiencyEN.get().setContent(homePageRequest.getContentEfficiencyOptimizationsEN());
            efficiencyOptimizationEntities.add(efficiencyEN.get());
        }

        Optional<EfficiencyOptimizationEntity> creativeVN = efficiencyOptimizationRepository.findById(3L);
        if (creativeVN.isPresent()) {
            creativeVN.get().setTitle(homePageRequest.getTitleCreativeVN());
            creativeVN.get().setContent(homePageRequest.getContentCreativeVN());
            efficiencyOptimizationEntities.add(creativeVN.get());
        }
        Optional<EfficiencyOptimizationEntity> creativeEN = efficiencyOptimizationRepository.findById(4L);
        if (creativeEN.isPresent()) {
            creativeEN.get().setTitle(homePageRequest.getTitleCreativeEN());
            creativeEN.get().setContent(homePageRequest.getContentCreativeEN());
            efficiencyOptimizationEntities.add(creativeEN.get());
        }
        efficiencyOptimizationRepository.saveAll(efficiencyOptimizationEntities);
        advantageRepository.deleteAll();
        List<AdvantageEntity> advantageEntities = new ArrayList<>();
        List<AdvantageRequest> advantages = homePageRequest.getAdvantages();
        for (AdvantageRequest requestVN : advantages) {
            AdvantageEntity advantageVN = new AdvantageEntity();
            advantageVN.setContent(requestVN.getContentVN());
            advantageVN.setTitle(requestVN.getTitleVN());
            advantageVN.setLanguage("VN");
            advantageVN.setCreated(new Date());
            advantageEntities.add(advantageVN);
        }
        for (AdvantageRequest requestEN : advantages) {
            AdvantageEntity advantageEN = new AdvantageEntity();
            advantageEN.setContent(requestEN.getContentEN());
            advantageEN.setTitle(requestEN.getTitleEN());
            advantageEN.setLanguage("EN");
            advantageEN.setCreated(new Date());
            advantageEntities.add(advantageEN);
        }
        advantageRepository.saveAll(advantageEntities);
        return null;
    }

    public Object getPartner(int partnerID) throws GeneralException {
        try {
            return new BaseResponse<>(HttpStatus.OK.value(), "success", brandRepository.getByLogo(partnerID));
        } catch (Exception e) {
            throw new GeneralException(e.getLocalizedMessage());
        }
    }
}
