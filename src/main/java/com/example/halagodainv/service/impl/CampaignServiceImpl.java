package com.example.halagodainv.service.impl;


import com.example.halagodainv.config.Constant;
import com.example.halagodainv.dto.campain.CampaignDetailDto;
import com.example.halagodainv.dto.campain.CampaignDto;
import com.example.halagodainv.exception.ErrorResponse;
import com.example.halagodainv.model.campaign.CampaignEntity;
import com.example.halagodainv.model.ImageProductEntity;
import com.example.halagodainv.model.IndustryEntity;
import com.example.halagodainv.model.campaign.WorkCommunicationCampaign;
import com.example.halagodainv.repository.BrandRepository;
import com.example.halagodainv.repository.campagin.CampaignRepository;
import com.example.halagodainv.repository.ImageProductRepository;
import com.example.halagodainv.repository.IndustryRepository;
import com.example.halagodainv.repository.campagin.WorkCategoryRepository;
import com.example.halagodainv.repository.campagin.WorkCommuniRepository;
import com.example.halagodainv.repository.campagin.WorkStatusRepository;
import com.example.halagodainv.request.campaign.CampaignAddRequest;
import com.example.halagodainv.request.campaign.CampaignEditRequest;
import com.example.halagodainv.request.campaign.CampaignFormSearch;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.response.PageResponse;
import com.example.halagodainv.service.CampaignService;
import com.example.halagodainv.until.DateUtilFormat;
import com.example.halagodainv.until.FileImageUtil;
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
import java.util.*;

@Service
@RequiredArgsConstructor
public class CampaignServiceImpl implements CampaignService {
    private final CampaignRepository campaignRepository;
    private final ImageProductRepository imageProductRepository;
    private final BrandRepository brandRepository;
    private final IndustryRepository industryRepository;
    private final WorkCategoryRepository workCategoryRepository;
    private final WorkCommuniRepository workCommuniRepository;
    private final WorkStatusRepository workStatusRepository;
    private final FileImageUtil fileImageUtil;

    @Override
    public Object getCampaigns(CampaignFormSearch campaignSearch) {
        int offset = 0;
        if (campaignSearch.getPageNo() > 0) {
            offset = campaignSearch.getPageNo() - 1;
        }
        Pageable pageable = PageRequest.of(offset, campaignSearch.getPageSize());
        int totalCountByCampaign = campaignRepository.countAllBy(campaignSearch.getIndustryId(), campaignSearch.getCommunicationId());
        List<CampaignEntity> campaignEntities = campaignRepository.getByCampaigns(campaignSearch.getIndustryId(), campaignSearch.getCommunicationId(), pageable);
        List<CampaignDto> campaignDtos = new ArrayList<>();
        campaignEntities.forEach(campaignEntity -> {
            campaignDtos.add(new CampaignDto(campaignEntity));
        });
        return new PageResponse<>(new PageImpl<>(campaignDtos, pageable, totalCountByCampaign));
    }
    @Override
    public Object getRelateToCampaigns(String industryId, int camId, int workStatus) {
        List<CampaignEntity> campaignEntities = campaignRepository.getByRelateToCampaigns(industryId, camId, workStatus);
        List<CampaignDto> campaignDtos = new ArrayList<>();
        campaignEntities.forEach(campaignEntity -> {
            campaignDtos.add(new CampaignDto(campaignEntity));
        });
        return campaignDtos;
    }

    @Override
    public Object getDetail(int campaignId) {
        Optional<CampaignEntity> editEntity = campaignRepository.findById(campaignId);
        if (editEntity.isPresent()) {
            CampaignDetailDto campaignDetailDto = new CampaignDetailDto(editEntity.get(), imageProductRepository.findByCampaignEntity_Id(editEntity.get().getId()));
            return new BaseResponse<>(HttpStatus.OK.value(), "Lấy dữ liệu chi tiết thành công", campaignDetailDto);
        }
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Lấy dữ liệu chi tiết thất bại", null);
    }

    @Override
    @Transactional
    public Object add(CampaignAddRequest campaignAddRequest) throws ParseException {
        try {
            CampaignEntity campaignEntity = new CampaignEntity();
            campaignEntity.setCampaignName(campaignAddRequest.getCampaignName());
            campaignEntity.setIndustryId(InfluencerServiceImpl.parseListIntegerToString(campaignAddRequest.getIndustryId()));
            if (campaignAddRequest.getIndustryId().size() > 0) {
                List<IndustryEntity> industryEntities = industryRepository.findByIdIn(campaignAddRequest.getIndustryId());
                StringJoiner stringJoiner = new StringJoiner(",");
                industryEntities.forEach(industryEntity -> {
                    stringJoiner.add(industryEntity.getIndustryName());
                });
            }
            campaignEntity.setImg(fileImageUtil.uploadImage(campaignAddRequest.getCampaignImage()));
            campaignEntity.setDescription(campaignAddRequest.getDescriptionCampaign());
            campaignEntity.setContent(campaignAddRequest.getDescriptionCandidatePerform());
            campaignEntity.setCampaignCategory(InfluencerServiceImpl.parseListIntegerToString(campaignAddRequest.getCampaignCategory()));
            campaignEntity.setCampaignCommunication(InfluencerServiceImpl.parseListIntegerToString(campaignAddRequest.getCampaignCommunication()));
            campaignEntity.setCampaignStatus(campaignAddRequest.getCampaignStatus());
            campaignEntity.setCreated(new Date());
            campaignEntity = campaignRepository.save(campaignEntity);
            List<ImageProductEntity> imageProductEntities = new ArrayList<>();
            CampaignEntity finalCampaignEntity = campaignEntity;
            if (campaignAddRequest.getImageProductAddRequests().size() > 0) {
                campaignAddRequest.getImageProductAddRequests().forEach(i -> {
                    ImageProductEntity imageProductEntity = new ImageProductEntity();
                    imageProductEntity.setImageProduct(fileImageUtil.uploadImage(i.getImageProduct()));
                    imageProductEntity.setCampaignEntity(finalCampaignEntity);
                    imageProductEntities.add(imageProductEntity);
                });
            }
            List<ImageProductEntity> response = imageProductRepository.saveAll(imageProductEntities);
            return new BaseResponse<>(HttpStatus.CREATED.value(), "Thêm mới thành công", new CampaignDto(campaignEntity, response));
        } catch (
                Exception e) {
            return new ErrorResponse(Constant.FAILED, "Thêm mới không thành công", null);
        }
    }

    @Override
    @Transactional
    public Object edit(CampaignEditRequest campaignEditRequest) throws ParseException {
//        try {
//            List<ErrorResponse> errorResponses = new ArrayList<>();
//            CampaignEntity editEntity = campaignRepository.findByCamId(campaignEditRequest.getId());
//            if (ObjectUtils.isEmpty(editEntity)) {
//                return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Dữ liệu Không tồn tại", null);
//            }
//            if (!campaignEditRequest.validate(errorResponses)) {
//                return errorResponses;
//            }
//            editEntity.setCampaignName(campaignEditRequest.getCampaignName());
//            editEntity.setDateStart(DateUtilFormat.converStringToDate(campaignEditRequest.getStartDate(), "yyyy-MM-dd"));
//            editEntity.setDateEnd(DateUtilFormat.converStringToDate(campaignEditRequest.getEndDate(), "yyyy-MM-dd"));
//            editEntity.setImg(fileImageUtil.uploadImage(campaignEditRequest.getCampaignImage()));
//            editEntity.setTitleCampaign(campaignEditRequest.getTitleCampaign());
//            editEntity.setTitleProduct(campaignEditRequest.getTitleProduct());
//            editEntity.setIndustryId(InfluencerServiceImpl.parseListIntegerToString(campaignEditRequest.getIndustryId()));
//            if (campaignEditRequest.getIndustryId().size() > 0) {
//                List<IndustryEntity> industryEntities = industryRepository.findByIdIn(campaignEditRequest.getIndustryId());
//                StringJoiner stringJoiner = new StringJoiner(", ");
//                industryEntities.forEach(industryEntity -> {
//                    stringJoiner.add(industryEntity.getIndustryName());
//                });
//                editEntity.setIndustry(stringJoiner.toString());
//            }
//            editEntity.setIdBrand(campaignEditRequest.getBrandId());
//            editEntity.setDescription(campaignEditRequest.getDescriptionCampaign());
//            editEntity.setContent(campaignEditRequest.getDescriptionCandidatePerform());
//            editEntity.setRewards(campaignEditRequest.getReward());
//            editEntity.setCampaignCategory(InfluencerServiceImpl.parseListIntegerToString(campaignEditRequest.getCampaignCategory()));
//            editEntity.setCampaignCommunication(InfluencerServiceImpl.parseListIntegerToString(campaignEditRequest.getCampaignCommunication()));
//            editEntity.setCampaignStatus(campaignEditRequest.getCampaignStatus());
//            editEntity = campaignRepository.save(editEntity);
//            List<ImageProductEntity> imageProductEntities = new ArrayList<>();
//            CampaignEntity finalEditEntity = editEntity;
//            imageProductRepository.deleteByCampaignEntity_Id(editEntity.getId());
//            if (campaignEditRequest.getImageProductAddRequests().size() > 0) {
//                campaignEditRequest.getImageProductAddRequests().forEach(i -> {
//                    ImageProductEntity imageProductEntity = new ImageProductEntity();
//                    imageProductEntity.setImageProduct(fileImageUtil.uploadImage(i.getImageProduct()));
//                    imageProductEntity.setCampaignEntity(finalEditEntity);
//                    imageProductEntities.add(imageProductEntity);
//                });
//            }
//            List<ImageProductEntity> response = imageProductRepository.saveAll(imageProductEntities);
//            return new BaseResponse<>(HttpStatus.OK.value(), "Sửa thành công", new CampaignDto(editEntity, response));
//        } catch (Exception e) {
//            return new ErrorResponse(Constant.FAILED, "Sửa không thành công", null);
//        }
        return null;
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

    public Object getCampaignCommunications() {
        return new BaseResponse<>(Constant.SUCCESS, "Lấy dữ liệu thành công", workCommuniRepository.findAll());
    }

    public Object getCampaignStatuses() {
        return new BaseResponse<>(Constant.SUCCESS, "Lấy dữ liệu thành công", workStatusRepository.findAll());
    }

    public Object getCampaignCategories() {
        return new BaseResponse<>(Constant.SUCCESS, "Lấy dữ liệu thành công", workCategoryRepository.findAll());
    }
}
