package com.example.halagodainv.service;

import com.example.halagodainv.model.viewdisplayentity.ViewBrandEntity;

import java.util.List;

public interface ViewBrandService {

    List<ViewBrandEntity> getBranView(String language);

    ViewBrandEntity getDetail(String language, Long id);
}
