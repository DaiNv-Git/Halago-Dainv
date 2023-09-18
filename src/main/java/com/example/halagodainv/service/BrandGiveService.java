package com.example.halagodainv.service;

import com.example.halagodainv.model.viewdisplayentity.BrandGiveEntity;
import com.example.halagodainv.request.brand.BrandGiveRequest;

import java.util.List;

public interface BrandGiveService {
    Object getAll(String language);

    Object getDetail();

    Object update(List<BrandGiveRequest> brandGiveEntities);
}
