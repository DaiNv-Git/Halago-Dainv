package com.example.halagodainv.service.impl;

import com.example.halagodainv.dto.BrandDto;
import com.example.halagodainv.model.BrandEntity;
import com.example.halagodainv.repository.BrandRepository;
import com.example.halagodainv.response.PageResponse;
import com.example.halagodainv.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    public PageResponse<BrandDto> getByListBrand(int pageNo, int pageSize, String brandName, Integer status) {
        int offset = 0;
        if (pageNo > 0) {
            offset = pageNo - 1;
        }
        int countALlBrands = brandRepository.countAllBy();
        Pageable pageable = PageRequest.of(offset, pageSize);
        List<BrandEntity> getBrandEntities = brandRepository.findByBrandNameAndStatus(brandName, status, pageable);
        List<BrandDto> brandDtos = new ArrayList<>();
        getBrandEntities.forEach(brandEntity -> {
            brandDtos.add(new BrandDto(brandEntity));
        });
        PageResponse<BrandDto> pageResponse;
        if (CollectionUtils.isEmpty(brandDtos)) {
            pageResponse = new PageResponse<>(new PageImpl<>(brandDtos, pageable, 0));
            return pageResponse;
        }
        pageResponse = new PageResponse<>(new PageImpl<>(brandDtos, pageable, countALlBrands));
        return pageResponse;
    }

    public BrandDto add() {

        return null;
    }
}
