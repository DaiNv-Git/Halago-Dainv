package com.example.halagodainv.service.impl;

import com.example.halagodainv.dto.BrandDto;
import com.example.halagodainv.exception.ErrorResponse;
import com.example.halagodainv.exception.GeneralException;
import com.example.halagodainv.model.BrandEntity;
import com.example.halagodainv.model.UserEntity;
import com.example.halagodainv.repository.BrandRepository;
import com.example.halagodainv.repository.UserRepository;
import com.example.halagodainv.request.brand.BrandAddRequest;
import com.example.halagodainv.request.brand.BrandEditRequest;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.response.PageResponse;
import com.example.halagodainv.service.BrandService;
import com.example.halagodainv.service.auth.UserServiceConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import utils.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final UserServiceConfig userServiceConfig;
    private final UserRepository userRepository;

    @Override
    public PageResponse<BrandDto> getByListBrand(int pageNo, int pageSize, String brandName, String startDate, String endDate) throws ParseException {
        int offset = 0;
        if (pageNo > 0) {
            offset = pageNo - 1;
        }
        int countALlBrands = brandRepository.countAllBy();
        Pageable pageable = PageRequest.of(offset, pageSize);
        List<BrandEntity> getBrandEntities = brandRepository.findByBrandNameAndStatus(brandName, pageable);
        List<BrandDto> brandDtos = new ArrayList<>();
        getBrandEntities.forEach(brandEntity -> {
            brandDtos.add(new BrandDto(brandEntity));
        });
        PageResponse<BrandDto> pageResponse;
        if (CollectionUtils.isEmpty(brandDtos)) {
            pageResponse = new PageResponse<>(new PageImpl<>(brandDtos, pageable, 0));
            return pageResponse;
        }
        pageResponse = new PageResponse<>(new PageImpl<>(brandDtos, pageable, countALlBrands));
        return pageResponse;
    }

    @Override
    public Object add(BrandAddRequest brandAddRequest, String email) throws GeneralException {
        UserDetails user = userServiceConfig.loadUserByUsername(email);
        Optional<UserEntity> userEntity = userRepository.findByEmail(user.getUsername());
        List<ErrorResponse> errorResponses = new ArrayList<>();
        if (!brandAddRequest.validate(errorResponses)) {
            return errorResponses;
        }
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setBrandName(brandAddRequest.getBrandName());
        brandEntity.setWebsite(brandAddRequest.getWebsite());
        brandEntity.setBrandPhone('0' + String.valueOf(brandAddRequest.getPhoneNumber()));
        brandEntity.setBrandEmail(userEntity.get().getEmail());
        brandEntity.setRepresentativeName(brandAddRequest.getRegisterName());
        brandEntity.setLogo(brandAddRequest.getLogo());
        brandEntity.setCreated(new Date());
        brandEntity = brandRepository.save(brandEntity);
        return new BaseResponse<>(HttpStatus.OK.value(), "add success", new BrandDto(brandEntity, userEntity.get().getPasswordHide()));
    }

    @Override
    public Object edit(BrandEditRequest brandEditRequest, String email) throws GeneralException {
        UserDetails user = userServiceConfig.loadUserByUsername(email);
        Optional<UserEntity> userEntity = userRepository.findByEmail(user.getUsername());
        Optional<BrandEntity> brandEntity = brandRepository.findById(brandEditRequest.getId());
        if (!brandEntity.isPresent()) {
            return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "id is not exit", null);
        }
        List<ErrorResponse> errorResponses = new ArrayList<>();
        if (!brandEditRequest.validate(errorResponses)) {
            return errorResponses;
        }
        brandEntity.get().setBrandName(brandEditRequest.getBrandName());
        brandEntity.get().setWebsite(brandEditRequest.getWebsite());
        brandEntity.get().setBrandPhone('0' + String.valueOf(brandEditRequest.getPhoneNumber()));
        brandEntity.get().setBrandEmail(userEntity.get().getEmail());
        brandEntity.get().setRepresentativeName(brandEditRequest.getRegisterName());
        brandEntity.get().setLogo(brandEditRequest.getLogo());
        brandEntity.get().setCreated(new Date());
        brandRepository.save(brandEntity.get());
        return new BaseResponse<>(HttpStatus.OK.value(), "edit success", new BrandDto(brandEntity.get(), userEntity.get().getPasswordHide()));
    }
}
