package com.example.halagodainv.service.impl;

import com.example.halagodainv.config.Constant;
import com.example.halagodainv.repository.IndustryRepository;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.service.IndustryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service@RequiredArgsConstructor
public class IndustryServiceImpl implements IndustryService {
    private final IndustryRepository industryRepository;
    public Object getByIndustry() {
        return new BaseResponse<>(Constant.SUCCESS, "Lấy nhãn hàng thành công", industryRepository.findAll());
    }
}
