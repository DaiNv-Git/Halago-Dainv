package com.example.halagodainv.service.impl;

import com.example.halagodainv.config.Constant;
import com.example.halagodainv.dto.brand.BrandDto;
import com.example.halagodainv.exception.ErrorResponse;
import com.example.halagodainv.exception.GeneralException;
import com.example.halagodainv.model.BrandEntity;
import com.example.halagodainv.repository.BrandRepository;
import com.example.halagodainv.repository.UserRepository;
import com.example.halagodainv.request.brand.BrandAddRequest;
import com.example.halagodainv.request.brand.BrandEditRequest;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.response.PageResponse;
import com.example.halagodainv.service.BrandService;
import com.example.halagodainv.until.FormatTimeSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final UserRepository userRepository;

    @Override
    public Object getByListBrand(int pageNo, int pageSize, String brandName, String startDate, String endDate) throws ParseException {
        try {
            int offset = 0;
            if (pageNo > 0) {
                offset = pageNo - 1;
            }
            int countALlBrands = brandRepository.countAllBy(brandName, FormatTimeSearch.getStart(startDate), FormatTimeSearch.getEndDate(endDate));
            Pageable pageable = PageRequest.of(offset, pageSize);
            List<BrandEntity> getBrandEntities = brandRepository.findByBrandNameAndCreatedDate(brandName, FormatTimeSearch.getStart(startDate), FormatTimeSearch.getEndDate(endDate), pageable);
            List<BrandDto> brandDtos = new ArrayList<>();
            getBrandEntities.forEach(brandEntity -> {
                brandDtos.add(new BrandDto(brandEntity));
            });
            PageResponse<BrandDto> pageResponse;
            if (CollectionUtils.isEmpty(brandDtos)) {
                pageResponse = new PageResponse<>(new PageImpl<>(brandDtos, pageable, 0));
                return new BaseResponse(HttpStatus.OK.value(), "Lấy dữ liệu thành công", pageResponse);
            }
            pageResponse = new PageResponse<>(new PageImpl<>(brandDtos, pageable, countALlBrands));
            return new BaseResponse(HttpStatus.OK.value(), "Lấy dữ liệu thành công", pageResponse);
        } catch (Exception e) {
            return new ErrorResponse(HttpStatus.OK.value(), "Lấy dữ liệu thất bại", null);
        }
    }

    @Override
    public Object getByDetail(int brandId) throws ParseException {
        Optional<BrandEntity> brandEntity = brandRepository.findById(brandId);
        if (!brandEntity.isPresent()) {
            return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Dữ liệu chi tiết không tồn tại", null);
        }
        return new BaseResponse<>(HttpStatus.OK.value(), "Lấy dữ liệu chi tiết thành công", new BrandDto(brandEntity.get()));
    }

    @Override
    public Object add(BrandAddRequest brandAddRequest, String email) throws GeneralException {
        try {
            List<ErrorResponse> errorResponses = new ArrayList<>();
            if (!brandAddRequest.validate(errorResponses)) {
                return errorResponses;
            }

            Optional<BrandEntity> emailBrand = brandRepository.findByBrandEmail(brandAddRequest.getEmail());
            Optional<BrandEntity> braneName = brandRepository.findByBrandName(brandAddRequest.getBrandName());
            if (braneName.isPresent() && emailBrand.isPresent()) {
                List<String> errors = new ArrayList<>();
                errors.add(brandAddRequest.getBrandName());
                errors.add(brandAddRequest.getEmail());
                return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Đã tồn tại email và nhãn hàng", errors);
            }

            BrandEntity brandEntity = new BrandEntity();
            brandEntity.setBrandName(brandAddRequest.getBrandName());
            brandEntity.setWebsite(brandAddRequest.getWebsite());
            brandEntity.setBrandPhone(brandAddRequest.getPhoneNumber());
            brandEntity.setBrandEmail(brandAddRequest.getEmail());
            brandEntity.setRepresentativeName(brandAddRequest.getRegisterName());
            brandEntity.setDescription(brandAddRequest.getDescription());
            brandEntity.setLogo(brandAddRequest.getLogo());
            brandEntity.setCreated(new Date());
            brandEntity = brandRepository.save(brandEntity);
            return new BaseResponse<>(HttpStatus.OK.value(), "Thêm dữ liệu thành công", new BrandDto(brandEntity));
        } catch (Exception e) {
            return new ErrorResponse(HttpStatus.NO_CONTENT.value(), "Dữ liệu không tồn tại", null);
        }

    }

    @Override
    public Object edit(BrandEditRequest brandEditRequest, String email) throws GeneralException {
        Optional<BrandEntity> brandEntity = brandRepository.findById(brandEditRequest.getId());
        if (brandEntity.isEmpty()) {
            return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Dữ liệu không tồn tại", null);
        }
        List<ErrorResponse> errorResponses = new ArrayList<>();
        if (!brandEditRequest.validate(errorResponses)) {
            return errorResponses;
        }
        brandEntity.get().setBrandName("");
        brandEntity.get().setBrandEmail("");
        brandRepository.save(brandEntity.get());

        Optional<BrandEntity> emailBrand = brandRepository.findByBrandEmail(brandEditRequest.getEmail());
        Optional<BrandEntity> braneName = brandRepository.findByBrandName(brandEditRequest.getBrandName());
        if (braneName.isPresent() && emailBrand.isPresent()) {
            List<String> errors = new ArrayList<>();
            errors.add(brandEditRequest.getBrandName());
            errors.add(brandEditRequest.getEmail());
            return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Đã tồn tại email và nhãn hàng", errors);
        }

        brandEntity.get().setBrandName(brandEditRequest.getBrandName());
        brandEntity.get().setWebsite(brandEditRequest.getWebsite());
        brandEntity.get().setBrandPhone(brandEditRequest.getPhoneNumber());
        brandEntity.get().setBrandEmail(brandEditRequest.getEmail());
        brandEntity.get().setRepresentativeName(brandEditRequest.getRegisterName());
        brandEntity.get().setLogo(brandEditRequest.getLogo());
        brandEntity.get().setPartnerId(brandEditRequest.getPartnerId());
        brandEntity.get().setDescription(brandEditRequest.getDescription());
        brandRepository.save(brandEntity.get());
        return new BaseResponse<>(HttpStatus.OK.value(), "edit success", new BrandDto(brandEntity.get()));
    }

    @Override
    public Object deleteByBranId(int brandId) {
        Optional<BrandEntity> brandEntity = brandRepository.findById(brandId);
        if (!brandEntity.isPresent()) {
            return new ErrorResponse(HttpStatus.OK.value(), "Xóa thất bại", null);
        }
        brandRepository.deleteById(brandId);
        return new BaseResponse<>(HttpStatus.OK.value(), "Xóa thành công", null);
    }

    public Object getByBrands() {
        return new BaseResponse<>(Constant.SUCCESS, "Lấy nhãn hàng thành công", brandRepository.findByBrandNameAndId());
    }
}
