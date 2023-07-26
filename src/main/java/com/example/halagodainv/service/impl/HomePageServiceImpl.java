package com.example.halagodainv.service.impl;

import com.example.halagodainv.dto.hompage.AdvantageDto;
import com.example.halagodainv.dto.hompage.EfficiencyOptimizationDto;
import com.example.halagodainv.dto.hompage.HomePageDto;
import com.example.halagodainv.dto.hompage.NewsTenDto;
import com.example.halagodainv.exception.GeneralException;
import com.example.halagodainv.repository.AdvantageRepository;
import com.example.halagodainv.repository.BrandRepository;
import com.example.halagodainv.repository.EfficiencyOptimizationRepository;
import com.example.halagodainv.repository.NewsRepository;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.service.HomePageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

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
            List<AdvantageDto> advantageDtos = advantageRepository.getAdvanTageLanguage(language);
            EfficiencyOptimizationDto efficiencyOptimization = efficiencyOptimizationRepository.getEfficiencyOptiLanguage(language);
            Pageable page = PageRequest.of(0, 10);
            List<NewsTenDto> newsTenDtos = newsRepository.getHomeLanguage(language, page);
            HomePageDto homePageDto = new HomePageDto(advantageDtos, efficiencyOptimization, newsTenDtos);
            return new BaseResponse<>(HttpStatus.OK.value(), "success", homePageDto);
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
