package com.example.halagodainv.service.impl.viewdisplay;

import com.example.halagodainv.model.viewdisplayentity.ViewBrandEntity;
import com.example.halagodainv.repository.viewdisplay.ViewBrandRepository;
import com.example.halagodainv.request.brand.VieBrandRequest;
import com.example.halagodainv.service.ViewBrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ViewBrandServiceImpl implements ViewBrandService {

    private final ViewBrandRepository viewBrandRepository;


    public List<ViewBrandEntity> getBranView(String language) {
        return viewBrandRepository.findByLanguage(language);
    }

    public ViewBrandEntity getDetail(String language, Long id) {
        return viewBrandRepository.findByLanguageAndId(language, id);
    }

    public ViewBrandEntity add(VieBrandRequest vieBrandRequest) {

        return null;
    }

}
