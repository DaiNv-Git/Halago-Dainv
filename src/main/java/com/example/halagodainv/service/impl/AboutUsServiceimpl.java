package com.example.halagodainv.service.impl;


import com.example.halagodainv.exception.ErrorResponse;
import com.example.halagodainv.model.AboutUsEntity;
import com.example.halagodainv.model.AboutUsLanguageEntity;
import com.example.halagodainv.repository.AboutUsLanguageRepository;
import com.example.halagodainv.repository.AboutUsRepository;
import com.example.halagodainv.request.about.AboutEditRequest;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.service.AboutUsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AboutUsServiceimpl implements AboutUsService {
    private final AboutUsRepository aboutUsRepository;

    private final AboutUsLanguageRepository aboutUsLanguageRepository;
    @Override
    public Object getDetail() {
        return new BaseResponse<>(HttpStatus.OK.value(), "Tìm kiếm thành công", aboutUsRepository.getByAbout());
    }
    @Override
    public Object update(AboutEditRequest aboutEditRequest) {
        try {
            AboutUsEntity aboutUsEntity = aboutUsRepository.findById(aboutEditRequest.getId());
            Optional<AboutUsLanguageEntity> aboutUsLanguageEntity = aboutUsLanguageRepository.findById(aboutEditRequest.getId());
            if (ObjectUtils.isEmpty(aboutUsEntity) && !aboutUsLanguageEntity.isPresent()) {
                return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Dữ liệu không tồn tại", null);
            }
            aboutUsEntity.setContent(aboutEditRequest.getContentVN());
            aboutUsEntity.setTitle(aboutEditRequest.getTitleVN());
            aboutUsEntity = aboutUsRepository.save(aboutUsEntity);
            aboutUsLanguageEntity.get().setContentEn(aboutEditRequest.getContentEN());
            aboutUsLanguageEntity.get().setTitleEn(aboutEditRequest.getTitleEN());
            aboutUsLanguageRepository.save(aboutUsLanguageEntity.get());
            return new BaseResponse<>(HttpStatus.OK.value(), "Tìm kiếm thành công", aboutUsRepository.getByAbout(aboutUsEntity.getId()));
        } catch (Exception e) {
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
        }
    }


}
