package com.example.halagodainv.service;

import com.example.halagodainv.dto.BrandDto;
import com.example.halagodainv.response.PageResponse;

import java.util.List;

public interface BrandService {
    PageResponse<BrandDto> getByListBrand(int pageNo, int pageSize, String brandName, Integer status);
}
