package com.example.halagodainv.service.impl;


import com.example.halagodainv.dto.CampaignDto;
import com.example.halagodainv.exception.ErrorResponse;
import com.example.halagodainv.model.CampaignEntity;
import com.example.halagodainv.model.ImageProductEntity;
import com.example.halagodainv.repository.CampaignRepository;
import com.example.halagodainv.repository.ImageProductRepository;
import com.example.halagodainv.request.campaign.CampaignAddRequest;
import com.example.halagodainv.request.campaign.CampaignEditRequest;
import com.example.halagodainv.request.campaign.CampaignSearch;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.response.PageResponse;
import com.example.halagodainv.service.CampaignService;
import com.example.halagodainv.until.DateUtilFormat;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
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

    public Object getCampaigns(CampaignSearch campaignSearch) {
        int offset = 0;
        if (campaignSearch.getPageNo() > 0) {
            offset = campaignSearch.getPageNo() - 1;
        }
        Pageable pageable = PageRequest.of(offset, campaignSearch.getPageSize());
        String startDateFormat = "1000-01-01";
        String endDateFormat = "9999-12-31";
        if (!Strings.isEmpty(campaignSearch.getStartDate())) {
            startDateFormat = campaignSearch.getStartDate();
        }
        if (!Strings.isEmpty(campaignSearch.getEndDate())) {
            endDateFormat = campaignSearch.getEndDate();
        }
        int totalCountByCampaign = campaignRepository.countAllBy();
        List<CampaignEntity> campaignEntities = campaignRepository.getByCampaigns(campaignSearch.getCampaignName(), startDateFormat + " 00:00:00", endDateFormat + " 23:59:59", pageable);
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

    @Override
    @Transactional
    public Object add(CampaignAddRequest campaignAddRequest) throws ParseException {
        List<ErrorResponse> errorResponses = new ArrayList<>();
        if (!campaignAddRequest.validate(errorResponses)) {
            return errorResponses;
        }
        CampaignEntity campaignEntity = new CampaignEntity();
        campaignEntity.setBrandName(campaignAddRequest.getBrandName());
        campaignEntity.setCampaignName(campaignAddRequest.getCampaignName());
        campaignEntity.setIndustry(campaignAddRequest.getIndustry());
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
        return new BaseResponse<>(HttpStatus.CREATED.value(), "add success", new CampaignDto(campaignEntity, response));
    }

    @Override
    @Transactional
    public Object edit(CampaignEditRequest campaignEditRequest) throws ParseException {
        List<ErrorResponse> errorResponses = new ArrayList<>();
        CampaignEntity editEntity = campaignRepository.findByCamId(campaignEditRequest.getId());
        if (ObjectUtils.isEmpty(editEntity)) {
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "campaign is not exits", null);
        }
        if (!campaignEditRequest.validate(errorResponses)) {
            return errorResponses;
        }
        editEntity.setBrandName(campaignEditRequest.getBrandName());
        editEntity.setCampaignName(campaignEditRequest.getCampaignName());
        editEntity.setIndustry(campaignEditRequest.getIndustry());
        editEntity.setDateStart(DateUtilFormat.converStringToDate(campaignEditRequest.getStartDate(), "dd-MM-yyyy"));
        editEntity.setDateEnd(DateUtilFormat.converStringToDate(campaignEditRequest.getEndDate(), "dd-MM-yyyy"));
        editEntity.setImg(campaignEditRequest.getCampaignImage());
        editEntity.setTitleCampaign(campaignEditRequest.getTitleCampaign());
        editEntity.setTitleProduct(campaignEditRequest.getTitleProduct());
        editEntity.setDescription(campaignEditRequest.getDescriptionCampaign());
        editEntity.setContent(campaignEditRequest.getDescriptionCandidatePerform());
        editEntity.setRewards(campaignEditRequest.getReward());
        editEntity = campaignRepository.save(editEntity);
        List<ImageProductEntity> imageProductEntities = new ArrayList<>();
        List<Integer> imageIds = new ArrayList<>();
        CampaignEntity finalEditEntity = editEntity;
        campaignEditRequest.getImageProductEditRequests().forEach(i -> {
            if (Boolean.TRUE.equals(i.isCheck())) {
                imageIds.add(i.getImageProductId());
            } else {
                if (i.getImageProductId() > 0) {
                    ImageProductEntity imageProductEntity = imageProductRepository.findByIdAndCampaignEntity_Id(i.getImageProductId(), finalEditEntity.getId()).get();
                    imageProductEntity.setImageProduct(i.getImageProduct());
                    imageProductEntities.add(imageProductEntity);
                } else {
                    ImageProductEntity imageProductEntity = new ImageProductEntity();
                    imageProductEntity.setImageProduct(i.getImageProduct());
                    imageProductEntity.setCampaignEntity(finalEditEntity);
                    imageProductEntities.add(imageProductEntity);
                }
            }
        });
        imageProductRepository.deleteByIdIn(imageIds);
        List<ImageProductEntity> response = imageProductRepository.saveAll(imageProductEntities);
        return new BaseResponse<>(HttpStatus.OK.value(), "edit success", new CampaignDto(editEntity, response));
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
}
