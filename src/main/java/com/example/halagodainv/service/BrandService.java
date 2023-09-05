package com.example.halagodainv.service;

import com.example.halagodainv.dto.brand.BrandDto;
import com.example.halagodainv.exception.GeneralException;
import com.example.halagodainv.request.brand.BrandAddRequest;
import com.example.halagodainv.request.brand.BrandEditRequest;
import com.example.halagodainv.response.PageResponse;

import java.io.IOException;
import java.text.ParseException;

public interface BrandService {
    Object getByListBrand(int pageNo, int pageSize, String brandName, String startDate,String endDate) throws ParseException;
    Object getByDetail(int brandId) throws ParseException;
    Object add(BrandAddRequest brandAddRequest, String email) throws GeneralException;
    Object edit(BrandEditRequest brandEditRequest, String email) throws GeneralException, IOException;
    Object deleteByBranId(int brandId);

    Object getByBrands();
}
