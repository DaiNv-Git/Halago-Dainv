package com.example.halagodainv.service;

import com.example.halagodainv.request.kolCeleb.KolCelebRequest;

public interface KolCelebService {
    Object getKolAll(String language);

    Object getDetail();

    Object getDetailCeleb(Long id, String language);

    Object update(KolCelebRequest request);
}
