package com.example.halagodainv.service.impl;


import com.example.halagodainv.exception.ErrorResponse;
import com.example.halagodainv.model.FooterEntity;
import com.example.halagodainv.model.FooterLanguageEntity;
import com.example.halagodainv.repository.FooterLanguageRepository;
import com.example.halagodainv.repository.FooterRepository;
import com.example.halagodainv.request.foot.FootEditRequest;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.service.FooterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FooterServiceImpl implements FooterService {
    private final FooterRepository footerRepository;
    private final FooterLanguageRepository footerLanguageRepository;

    public Object getFoot() {
        try {
            return new BaseResponse<>(HttpStatus.OK.value(), "Tìm kiếm dữ liệu thành công", footerRepository.getFooter());
        } catch (Exception e) {
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
        }
    }

    public Object update(FootEditRequest footEditRequest) {
        try {
            Optional<FooterEntity> footer = footerRepository.findById(footEditRequest.getId());
            Optional<FooterLanguageEntity> footerLanguage = footerLanguageRepository.findById(footEditRequest.getId());
            if (!footer.isPresent() && !footerLanguage.isPresent()) {
                return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Dữ liệu không tôn tại!", null);
            }
            footer.get().setHotline(footEditRequest.getHotline());
            footer.get().setCompany(footEditRequest.getCompanyVN());
            footer.get().setAddress(footEditRequest.getAddressVN());
            footer.get().setFacebook(footEditRequest.getFacebook());
            footer.get().setEmail(footEditRequest.getEmail());
            footer.get().setInstagram(footEditRequest.getInstagram());
            footer.get().setZalo(footEditRequest.getZalo());
            footer.get().setYoutube(footEditRequest.getYoutube());
            footer.get().setTiktok(footEditRequest.getTiktok());
            footerRepository.save(footer.get());

            footerLanguage.get().setCompany(footEditRequest.getCompanyEN());
            footerLanguage.get().setAddress(footEditRequest.getAddressEN());
            footerLanguageRepository.save(footerLanguage.get());
            return new BaseResponse<>(HttpStatus.OK.value(), "Chỉnh sửa dữ liệu thành công", footerRepository.getFooter(footer.get().getId()));
        } catch (Exception e) {
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
        }
    }


}
