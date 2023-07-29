package com.example.halagodainv.service.impl;

import com.example.halagodainv.dto.hompage.*;
import com.example.halagodainv.exception.GeneralException;
import com.example.halagodainv.model.AdvantageEntity;
import com.example.halagodainv.model.AdvantageEntityLanguage;
import com.example.halagodainv.model.EfficiencyOptimizationEntity;
import com.example.halagodainv.repository.*;
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
    private final AdvantageLanguageRepository advantageLanguageRepository;
    private final BrandRepository brandRepository;

    @Override
    public Object getHomePage(String language) throws GeneralException {
        try {
            OriginativeDto originative = efficiencyOptimizationRepository.getOriginativeLanguage(language);
            List<AdvantageMapEntityDto> advantageMapEntityDtos = advantageRepository.getAdvantageLanguage();
            List<AdvantageDto> advantageDtos = new ArrayList<>();
            advantageMapEntityDtos.forEach(i -> {
                AdvantageDto advantageDto = new AdvantageDto();
                if (language.equalsIgnoreCase("VN")) {
                    advantageDto.setTitle(i.getTitleVN());
                    advantageDto.setContent(i.getContentVN());
                } else {
                    advantageDto.setTitle(i.getTitleEN());
                    advantageDto.setContent(i.getContentEN());
                }
                advantageDtos.add(advantageDto);
            });
            EfficiencyOptimizationDto efficiencyOptimization = efficiencyOptimizationRepository.getEfficiencyOptiLanguage(language);
            Pageable page = PageRequest.of(0, 10);
            List<NewsTenDto> newsTenDtos = newsRepository.getHomeLanguage(page);
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
            List<AdvantageMapEntityDto> advantageMapEntityDtos = advantageRepository.getAdvantageLanguage();
            HomePageDetail homePageDetail = new HomePageDetail(efficiencyVN, Creatives, advantageMapEntityDtos);
            return new BaseResponse<>(HttpStatus.OK.value(), "success", homePageDetail);
        } catch (Exception e) {
            throw new GeneralException(e.getLocalizedMessage());
        }
    }

    public Object updateHomePage(HomePageRequest homePageRequest) throws GeneralException {
        try {
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
            advantageLanguageRepository.deleteAll();
            advantageRepository.deleteAll();
            List<AdvantageRequest> advantages = homePageRequest.getAdvantages();
            for (AdvantageRequest request : advantages) {
                AdvantageEntity advantageVN = new AdvantageEntity();
                advantageVN.setContent(request.getContentVN());
                advantageVN.setTitle(request.getTitleVN());
                advantageVN.setCreated(new Date());
                advantageVN = advantageRepository.save(advantageVN);
                AdvantageEntityLanguage advantageEN = new AdvantageEntityLanguage();
                advantageEN.setContent(request.getContentEN());
                advantageEN.setTitle(request.getTitleEN());
                advantageEN.setAdvanId(advantageVN.getId());
                advantageLanguageRepository.save(advantageEN);
            }
            return new BaseResponse<>(HttpStatus.OK.value(), "success", getDetail());
        } catch (Exception e) {
            throw new GeneralException(e.getLocalizedMessage());
        }
    }

    public Object getPartner(int partnerID) throws GeneralException {
        try {
            return new BaseResponse<>(HttpStatus.OK.value(), "success", brandRepository.getByLogo(partnerID));
        } catch (Exception e) {
            throw new GeneralException(e.getLocalizedMessage());
        }
    }
}
