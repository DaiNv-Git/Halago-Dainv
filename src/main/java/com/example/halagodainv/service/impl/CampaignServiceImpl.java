package com.example.halagodainv.service.impl;


import com.example.halagodainv.config.Constant;
import com.example.halagodainv.dto.campain.CampaignDto;
import com.example.halagodainv.exception.ErrorResponse;
import com.example.halagodainv.model.CampaignEntity;
import com.example.halagodainv.model.ImageProductEntity;
import com.example.halagodainv.model.IndustryEntity;
import com.example.halagodainv.repository.BrandRepository;
import com.example.halagodainv.repository.CampaignRepository;
import com.example.halagodainv.repository.ImageProductRepository;
import com.example.halagodainv.repository.IndustryRepository;
import com.example.halagodainv.request.campaign.CampaignAddRequest;
import com.example.halagodainv.request.campaign.CampaignEditRequest;
import com.example.halagodainv.request.campaign.CampaignFormSearch;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.response.PageResponse;
import com.example.halagodainv.service.CampaignService;
import com.example.halagodainv.until.DateUtilFormat;
import com.example.halagodainv.until.FormatTimeSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CampaignServiceImpl implements CampaignService {
    private final CampaignRepository campaignRepository;
    private final ImageProductRepository imageProductRepository;
    private final BrandRepository brandRepository;
    private final IndustryRepository industryRepository;

    @Override
    public Object getCampaigns(CampaignFormSearch campaignSearch) {
        int offset = 0;
        if (campaignSearch.getPageNo() > 0) {
            offset = campaignSearch.getPageNo() - 1;
        }
        Pageable pageable = PageRequest.of(offset, campaignSearch.getPageSize());
        int totalCountByCampaign = campaignRepository.countAllBy(campaignSearch.getCampaignName(), FormatTimeSearch.getStart(campaignSearch.getStartDate()), FormatTimeSearch.getEndDate(campaignSearch.getEndDate()));
        List<CampaignEntity> campaignEntities = campaignRepository.getByCampaigns(campaignSearch.getCampaignName(),
                FormatTimeSearch.getStart(campaignSearch.getStartDate()), FormatTimeSearch.getEndDate(campaignSearch.getEndDate()), pageable);
        List<CampaignDto> campaignDtos = new ArrayList<>();
        campaignEntities.forEach(campaignEntity -> {
            campaignDtos.add(new CampaignDto(campaignEntity, imageProductRepository.findByCampaignEntity_Id(campaignEntity.getId())));
        });
        PageResponse<CampaignDto> pageResponse;
        if (CollectionUtils.isEmpty(campaignDtos)) {
            pageResponse = new PageResponse<>(new PageImpl<>(campaignDtos, pageable, 0));
            return pageResponse;
        }
        pageResponse = new PageResponse<>(new PageImpl<>(campaignDtos, pageable, totalCountByCampaign));
        return pageResponse;
    }


    public Object getDetail(int campaignId) {
        Optional<CampaignEntity> editEntity = campaignRepository.findById(campaignId);
        if (editEntity.isPresent()) {
            CampaignDto campaignDto = new CampaignDto(editEntity.get(), imageProductRepository.findByCampaignEntity_Id(editEntity.get().getId()));
            return new BaseResponse<>(HttpStatus.OK.value(), "Lấy dữ liệu chi tiết thành công", campaignDto);
        }
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Lấy dữ liệu chi tiết thất bại", null);
    }

    @Override
    @Transactional
    public Object add(CampaignAddRequest campaignAddRequest) throws ParseException {
        try {
            List<ErrorResponse> errorResponses = new ArrayList<>();
            if (!campaignAddRequest.validate(errorResponses)) {
                return errorResponses;
            }
            CampaignEntity campaignEntity = new CampaignEntity();
            campaignEntity.setCampaignName(campaignAddRequest.getCampaignName());
            campaignEntity.setIndustryId(campaignAddRequest.getIndustryId());
            Optional<IndustryEntity> industryEntity = industryRepository.findById(campaignAddRequest.getIndustryId());
            if (industryEntity.isPresent()) {
                campaignEntity.setIndustry(industryEntity.get().getIndustryName());
            }
            campaignEntity.setIdBrand(campaignAddRequest.getBrandId());
            campaignEntity.setDateStart(DateUtilFormat.converStringToDate(campaignAddRequest.getStartDate(), "yyyy-MM-dd"));
            campaignEntity.setDateEnd(DateUtilFormat.converStringToDate(campaignAddRequest.getEndDate(), "yyyy-MM-dd"));
            campaignEntity.setImg(campaignAddRequest.getCampaignImage());
            campaignEntity.setTitleCampaign(campaignAddRequest.getTitleCampaign());
            campaignEntity.setTitleProduct(campaignAddRequest.getTitleProduct());
            campaignEntity.setDescription(campaignAddRequest.getDescriptionCampaign());
            campaignEntity.setContent(campaignAddRequest.getDescriptionCandidatePerform());
            campaignEntity.setRewards(campaignAddRequest.getReward());
            campaignEntity.setCreated(new Date());
            campaignEntity = campaignRepository.save(campaignEntity);
            List<ImageProductEntity> imageProductEntities = new ArrayList<>();
            CampaignEntity finalCampaignEntity = campaignEntity;
            campaignAddRequest.getImageProductAddRequests().forEach(i -> {
                ImageProductEntity imageProductEntity = new ImageProductEntity();
                imageProductEntity.setImageProduct(i.getImageProduct());
                imageProductEntity.setCampaignEntity(finalCampaignEntity);
                imageProductEntities.add(imageProductEntity);
            });
            List<ImageProductEntity> response = imageProductRepository.saveAll(imageProductEntities);
            return new BaseResponse<>(HttpStatus.CREATED.value(), "Thêm mới thành công", new CampaignDto(campaignEntity, response));
        } catch (Exception e) {
            return new ErrorResponse(Constant.FAILED, "Thêm mới không thành công", null);
        }
    }

    @Override
    @Transactional
    public Object edit(CampaignEditRequest campaignEditRequest) throws ParseException {
        try {
            List<ErrorResponse> errorResponses = new ArrayList<>();
            CampaignEntity editEntity = campaignRepository.findByCamId(campaignEditRequest.getId());
            if (ObjectUtils.isEmpty(editEntity)) {
                return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Dữ liệu Không tồn tại", null);
            }
            if (!campaignEditRequest.validate(errorResponses)) {
                return errorResponses;
            }
            editEntity.setCampaignName(campaignEditRequest.getCampaignName());
            editEntity.setDateStart(DateUtilFormat.converStringToDate(campaignEditRequest.getStartDate(), "yyyy-MM-dd"));
            editEntity.setDateEnd(DateUtilFormat.converStringToDate(campaignEditRequest.getEndDate(), "yyyy-MM-dd"));
            editEntity.setImg(campaignEditRequest.getCampaignImage());
            editEntity.setTitleCampaign(campaignEditRequest.getTitleCampaign());
            editEntity.setTitleProduct(campaignEditRequest.getTitleProduct());
            editEntity.setIndustryId(campaignEditRequest.getIndustryId());
            Optional<IndustryEntity> industryEntity = industryRepository.findById(campaignEditRequest.getIndustryId());
            if (industryEntity.isPresent()) {
                editEntity.setIndustry(industryEntity.get().getIndustryName());
            } else {
                editEntity.setIndustry("");
            }
            editEntity.setIdBrand(campaignEditRequest.getBrandId());
            editEntity.setDescription(campaignEditRequest.getDescriptionCampaign());
            editEntity.setContent(campaignEditRequest.getDescriptionCandidatePerform());
            editEntity.setRewards(campaignEditRequest.getReward());
            editEntity = campaignRepository.save(editEntity);
            List<ImageProductEntity> imageProductEntities = new ArrayList<>();
            CampaignEntity finalEditEntity = editEntity;
            imageProductRepository.deleteByCampaignEntity_Id(editEntity.getId());
            campaignEditRequest.getImageProductAddRequests().forEach(i -> {
                ImageProductEntity imageProductEntity = new ImageProductEntity();
                imageProductEntity.setImageProduct(i.getImageProduct());
                imageProductEntity.setCampaignEntity(finalEditEntity);
                imageProductEntities.add(imageProductEntity);
            });
            List<ImageProductEntity> response = imageProductRepository.saveAll(imageProductEntities);
            return new BaseResponse<>(HttpStatus.OK.value(), "Sửa thành công", new CampaignDto(editEntity, response));
        } catch (Exception e) {
            return new ErrorResponse(Constant.FAILED, "Sửa không thành công", null);
        }

    }

    public Object deleteByCampaign(int campaignId) {
        Optional<CampaignEntity> optionalCampaignEntity = campaignRepository.findById(campaignId);
        if (!optionalCampaignEntity.isPresent()) {
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "campagin is not exit!", null);
        }
        imageProductRepository.deleteByCampaignEntity_Id(campaignId);
        campaignRepository.deleteById(campaignId);
        return new BaseResponse<>(HttpStatus.OK.value(), "delete success", null);
    }

    public Object getByBrands() {
        return new BaseResponse<>(Constant.SUCCESS, "Lấy nhãn hàng thành công", brandRepository.findByBrandNameAndId());
    }

    public Object getByIndustry() {
        return new BaseResponse<>(Constant.SUCCESS, "Lấy nhãn hàng thành công", industryRepository.findAll());
    }
}
