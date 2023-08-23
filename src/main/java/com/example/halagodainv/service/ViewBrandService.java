package com.example.halagodainv.service;

import com.example.halagodainv.dto.brand.ViewBrandDto;
import com.example.halagodainv.model.viewdisplayentity.ViewBrandEntity;
import com.example.halagodainv.request.brand.ViewBrandRequest;
import com.example.halagodainv.response.PageResponse;

import java.util.List;

public interface ViewBrandService {

    List<ViewBrandDto> getBranViews(String language);

    PageResponse<?> getAll(int pageNo, int pageSize);

    ViewBrandEntity getDetail(Long id);

    ViewBrandEntity add(ViewBrandRequest viewBrandRequest);

    ViewBrandEntity edit(ViewBrandRequest viewBrandRequest);

    void delete(Long viewId);
}
