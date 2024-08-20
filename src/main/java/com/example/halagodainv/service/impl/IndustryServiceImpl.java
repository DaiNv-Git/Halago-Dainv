package com.example.halagodainv.service.impl;

import com.example.halagodainv.config.Constant;
import com.example.halagodainv.dto.campain.ConvertData;
import com.example.halagodainv.model.IndustryEntity;
import com.example.halagodainv.repository.IndustryRepository;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.service.IndustryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IndustryServiceImpl implements IndustryService {
    private final IndustryRepository industryRepository;

    public Object getByIndustry(String language) {
        List<IndustryEntity> results = industryRepository.findAll();
        return new BaseResponse<>(Constant.SUCCESS, "Lấy nhãn hàng thành công", results.stream().map(result ->
                ConvertData.from(result, language)).collect(Collectors.toList()));
    }
}
