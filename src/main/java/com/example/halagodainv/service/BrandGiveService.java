package com.example.halagodainv.service;

import com.example.halagodainv.model.viewdisplayentity.BrandGiveEntity;

import java.util.List;

public interface BrandGiveService {
    Object getAll(String language);

    Object getDetail();

    Object update(List<BrandGiveEntity> brandGiveEntities);
}
