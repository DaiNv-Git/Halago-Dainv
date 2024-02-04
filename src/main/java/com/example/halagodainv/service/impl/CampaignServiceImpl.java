package com.example.halagodainv.service.impl;


import com.example.halagodainv.config.Constant;
import com.example.halagodainv.dto.campain.CampaignDetailDto;
import com.example.halagodainv.dto.campain.CampaignDetailFullDto;
import com.example.halagodainv.dto.campain.CampaignDto;
import com.example.halagodainv.dto.campain.CampaignRecruitment;
import com.example.halagodainv.exception.ErrorResponse;
import com.example.halagodainv.model.campaign.CampaignEntity;
import com.example.halagodainv.model.campaign.CampaignRecruitmentLogEntity;
import com.example.halagodainv.repository.BrandRepository;
import com.example.halagodainv.repository.campagin.*;
import com.example.halagodainv.repository.IndustryRepository;
import com.example.halagodainv.request.campaign.CampaignAddRequest;
import com.example.halagodainv.request.campaign.CampaignEditRequest;
import com.example.halagodainv.request.campaign.CampaignFormSearch;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.response.CampaignUserResponse;
import com.example.halagodainv.response.PageResponse;
import com.example.halagodainv.service.CampaignService;
import com.example.halagodainv.until.DateUtilFormat;
import com.example.halagodainv.until.FileImageUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CampaignServiceImpl implements CampaignService {
    private final CampaignRepository campaignRepository;
    private final BrandRepository brandRepository;
    private final IndustryRepository industryRepository;
    private final WorkCategoryRepository workCategoryRepository;
    private final WorkCommuniRepository workCommuniRepository;
    private final WorkStatusRepository workStatusRepository;
    private final FileImageUtil fileImageUtil;

    private final CampaignRecruitmentLog campaignRecruitmentLog;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Object getCampaigns(CampaignFormSearch campaignSearch) {
        int offset = 0;
        if (campaignSearch.getPageNo() > 0) {
            offset = campaignSearch.getPageNo() - 1;
        }
        Pageable pageable = PageRequest.of(offset, campaignSearch.getPageSize());
        int totalCountByCampaign = campaignRepository.countAllBy(campaignSearch.getIndustryId(), campaignSearch.getCommunicationId(), campaignSearch.getCampaginName());
        List<CampaignEntity> campaignEntities = campaignRepository.getByCampaigns(campaignSearch.getIndustryId(), campaignSearch.getCommunicationId(), campaignSearch.getCampaginName(), pageable);
        List<CampaignDto> campaignDtos = new ArrayList<>();
        campaignEntities.forEach(campaignEntity -> campaignDtos.add(new CampaignDto(campaignEntity, campaignSearch.getLanguage())));
        return new PageResponse<>(new PageImpl<>(campaignDtos, pageable, totalCountByCampaign));
    }

    @Override
    public Object getRelateToCampaigns(List<Integer> industryIds, int camId, int workStatus, String language) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select * from campaign cam where ");
        String industry = InfluencerServiceImpl.parseListIntegerToString(industryIds);
        if (!StringUtils.isEmpty(industry) && campaignRepository.findByIdAndIndustryId(camId, industry).isPresent()) {
            stringBuilder.append("( ");
            for (int i = 0; i < industryIds.size(); i++) {
                stringBuilder.append("cam.industry_id like '%").append(industryIds.get(i)).append("%'");
                if (i < industryIds.size() - 1) {
                    stringBuilder.append(" or ");
                }
            }
            stringBuilder.append(") and ");
        }
        stringBuilder.append("cam.id <> ").append(camId).append(" and cam.work_status = ").append(workStatus).append(" limit 10");
        Query nativeQuery = entityManager.createNativeQuery(stringBuilder.toString(), CampaignEntity.class);
        List<CampaignEntity> campaignEntities = nativeQuery.getResultList();
        List<CampaignDto> campaignDtos = new ArrayList<>();
        campaignEntities.forEach(campaignEntity -> campaignDtos.add(new CampaignDto(campaignEntity, language)));
        return campaignDtos;
    }

    @Override
    public Object getDetail(int campaignId, String language) {
        Optional<CampaignEntity> editEntity = campaignRepository.findById(campaignId);
        if (editEntity.isPresent()) {
            CampaignDetailDto campaignDetailDto = new CampaignDetailDto(editEntity.get(), language);
            return new BaseResponse<>(HttpStatus.OK.value(), "Lấy dữ liệu chi tiết thành công", campaignDetailDto);
        }
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Lấy dữ liệu chi tiết thất bại", null);
    }

    @Override
    public Object getDetailFull(int campaignId) {
        Optional<CampaignEntity> editEntity = campaignRepository.findById(campaignId);
        if (editEntity.isPresent()) {
            CampaignDetailFullDto campaignDetailFullDto = new CampaignDetailFullDto(editEntity.get());
            return new BaseResponse<>(HttpStatus.OK.value(), "Lấy dữ liệu chi tiết thành công", campaignDetailFullDto);
        }
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Lấy dữ liệu chi tiết thất bại", null);
    }

    @Override
    @Transactional
    public Object add(CampaignAddRequest campaignAddRequest) throws ParseException {
        try {
            CampaignEntity campaignEntity = new CampaignEntity();
            campaignEntity.setCampaignName(campaignAddRequest.getCampaignName());
            campaignEntity.setCampaignNameEN(campaignAddRequest.getCampaignNameEN());
            campaignEntity.setIndustryId(InfluencerServiceImpl.parseListIntegerToString(campaignAddRequest.getIndustryId()));
            campaignEntity.setImg(fileImageUtil.uploadImage(campaignAddRequest.getImage()));
            campaignEntity.setCampaignCategory(InfluencerServiceImpl.parseListIntegerToString(campaignAddRequest.getCampaignCategory()));
            campaignEntity.setCampaignCommunication(InfluencerServiceImpl.parseListIntegerToString(campaignAddRequest.getCampaignCommunication()));
            campaignEntity.setWorkStatus(campaignAddRequest.getWorkStatus());
            campaignEntity.setImage1(fileImageUtil.uploadImage(campaignAddRequest.getThumbnail1()));
            campaignEntity.setImage2(fileImageUtil.uploadImage(campaignAddRequest.getThumbnail2()));
            campaignEntity.setImage3(fileImageUtil.uploadImage(campaignAddRequest.getThumbnail3()));
            campaignEntity.setConditionApply(campaignAddRequest.getConditionApply());
            campaignEntity.setConditionApplyEN(campaignAddRequest.getConditionApplyEN());
            campaignEntity.setContent(campaignAddRequest.getContent());
            campaignEntity.setContentEN(campaignAddRequest.getContentEN());
            campaignEntity.setMethod(campaignAddRequest.getMethod());
            campaignEntity.setMethodEN(campaignAddRequest.getMethodEN());
            campaignEntity.setOutstandingProduct(campaignAddRequest.getOutstandingProduct());
            campaignEntity.setOutstandingProductEN(campaignAddRequest.getOutstandingProductEN());
            campaignEntity.setOther(campaignAddRequest.getOther());
            campaignEntity.setOtherEN(campaignAddRequest.getOtherEN());
            campaignEntity.setHashtag(campaignAddRequest.getHashtag());
            campaignEntity.setTimeDeadline(campaignAddRequest.getTimeDeadline());
            campaignEntity.setCreated(DateUtilFormat.newDateAsia());
            campaignEntity = campaignRepository.save(campaignEntity);
            return new BaseResponse<>(HttpStatus.CREATED.value(), "Thêm mới thành công", new CampaignDetailFullDto(campaignEntity));
        } catch (
                Exception e) {
            return new ErrorResponse<>(Constant.FAILED, "Thêm mới không thành công", null);
        }
    }

    @Override
    @Transactional
    public Object edit(CampaignEditRequest campaignEditRequest) throws ParseException {
        try {
            CampaignEntity editEntity = campaignRepository.findByCamId(campaignEditRequest.getId());
            if (ObjectUtils.isEmpty(editEntity)) {
                return new ErrorResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Dữ liệu Không tồn tại", null);
            }

            editEntity.setCampaignName(campaignEditRequest.getCampaignName());
            editEntity.setCampaignNameEN(campaignEditRequest.getCampaignNameEN());
            editEntity.setIndustryId(InfluencerServiceImpl.parseListIntegerToString(campaignEditRequest.getIndustryId()));
            editEntity.setImg(fileImageUtil.uploadImage(campaignEditRequest.getImage()));
            editEntity.setCampaignCategory(InfluencerServiceImpl.parseListIntegerToString(campaignEditRequest.getCampaignCategory()));
            editEntity.setCampaignCommunication(InfluencerServiceImpl.parseListIntegerToString(campaignEditRequest.getCampaignCommunication()));
            editEntity.setWorkStatus(campaignEditRequest.getWorkStatus());
            editEntity.setImage1(fileImageUtil.uploadImage(campaignEditRequest.getThumbnail1()));
            editEntity.setImage2(fileImageUtil.uploadImage(campaignEditRequest.getThumbnail2()));
            editEntity.setImage3(fileImageUtil.uploadImage(campaignEditRequest.getThumbnail3()));
            editEntity.setConditionApply(campaignEditRequest.getConditionApply());
            editEntity.setConditionApplyEN(campaignEditRequest.getConditionApplyEN());
            editEntity.setContent(campaignEditRequest.getContent());
            editEntity.setContentEN(campaignEditRequest.getContentEN());
            editEntity.setMethod(campaignEditRequest.getMethod());
            editEntity.setMethodEN(campaignEditRequest.getMethodEN());
            editEntity.setOutstandingProduct(campaignEditRequest.getOutstandingProduct());
            editEntity.setOutstandingProductEN(campaignEditRequest.getOutstandingProductEN());
            editEntity.setOther(campaignEditRequest.getOther());
            editEntity.setOtherEN(campaignEditRequest.getOtherEN());
            editEntity.setHashtag(campaignEditRequest.getHashtag());
            editEntity.setTimeDeadline(campaignEditRequest.getTimeDeadline());
            editEntity = campaignRepository.save(editEntity);
            return new BaseResponse<>(HttpStatus.OK.value(), "Sửa thành công", new CampaignDetailFullDto(editEntity));
        } catch (Exception e) {
            return new ErrorResponse<>(Constant.FAILED, "Sửa không thành công", null);
        }
    }

    @Override
    public Object isCheckRecruitment(int idInflu, int idCampaign) {
        if (campaignRecruitmentLog.findByIdInfluAndIdCampaign(idInflu, idCampaign).isPresent()) {
            return new ErrorResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Tài khoản này đã ứng tuyển chiến dịch này!", null);
        }
        CampaignRecruitmentLogEntity campaignRecruitmentLogEntity = new CampaignRecruitmentLogEntity();
        campaignRecruitmentLogEntity.setIdCampaign(idCampaign);
        campaignRecruitmentLogEntity.setIdInflu(idInflu);
        campaignRecruitmentLog.save(campaignRecruitmentLogEntity);
        return new BaseResponse<>(HttpStatus.OK.value(), "Ứng tuyển thành công", null);
    }

    @Override
    public PageResponse<CampaignRecruitment> getRecruitmentList(int campaignId, int pageSize, int pageNo, String language) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select ");
        if (language.equals("vn")) {
            stringBuilder.append(" c.campaign_name as campaignName,");
        } else if (language.equals("en")) {
            stringBuilder.append(" c.campaign_name_en as campaignName,");
        }
        stringBuilder.append("c.id as campaignId,u.username as userName from campaign c " +
                "inner join campaign_recruitment_log crl on crl.id_campaign = c.id " +
                "inner join users u on u.id = crl.id_influ " +
                "inner join role_user ru on ru.id_role= u.role_id and ru.id_role = 3 " +
                "where c.id = :campaignId ");
        int offset = Math.max(0, pageNo - 1);
        Pageable pageable = PageRequest.of(offset, pageSize);
        Query nativeQuery = entityManager.createNativeQuery(stringBuilder.toString());
        nativeQuery.setParameter("campaignId", campaignId);
        List<CampaignRecruitment> campaignRecruitments = nativeQuery.unwrap(NativeQuery.class).
                setResultTransformer(Transformers.aliasToBean(CampaignRecruitment.class)).getResultList();
        return new PageResponse<>(new PageImpl<>(campaignRecruitments, pageable, campaignRecruitments.size()));
    }

    @Override
    public PageResponse<CampaignUserResponse> getRecruitmentUserList(int campaignId, String userName, String language, int pageSize, int pageNo, Pageable pageable) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select ");
        if (language.equals("vn")) {
            stringBuilder.append(" c.campaign_name as campaignName,");
        } else if (language.equals("en")) {
            stringBuilder.append(" c.campaign_name_en as campaignName,");
        }
        stringBuilder.append("u.username as userName,u.id as userId,u.email as email,u.phone as phoneNumber,crl.id as id  from campaign c " +
                "inner join campaign_recruitment_log crl on crl.id_campaign = c.id " +
                "inner join users u on u.id = crl.id_influ " +
                "inner join role_user ru on ru.id_role= u.role_id and ru.id_role = 3 " +
                "where c.id = :campaignId ");

        if (userName != null) {
            stringBuilder.append("and u.username LIKE :userName ");
        }

        Query nativeQuery = entityManager.createNativeQuery(stringBuilder.toString());
        nativeQuery.setParameter("campaignId", campaignId);
        if (userName != null) {
            nativeQuery.setParameter("userName", "%" + userName + "%");
        }

        List<CampaignUserResponse> campaignRecruitments = nativeQuery.unwrap(NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(CampaignUserResponse.class))
                .getResultList();

        return new PageResponse<>(new PageImpl<>(campaignRecruitments, pageable, campaignRecruitments.size()));
    }


    public Object deleteByCampaign(int campaignId) {
        Optional<CampaignEntity> optionalCampaignEntity = campaignRepository.findById(campaignId);
        if (!optionalCampaignEntity.isPresent()) {
            return new ErrorResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "campagin is not exit!", null);
        }
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
