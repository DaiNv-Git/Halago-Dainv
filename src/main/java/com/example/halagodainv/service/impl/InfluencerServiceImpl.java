package com.example.halagodainv.service.impl;


import com.example.halagodainv.config.Constant;
import com.example.halagodainv.dto.influcer.*;
import com.example.halagodainv.excel.InfluencerExcel;
import com.example.halagodainv.excel.imports.DownFileImportExcel;
import com.example.halagodainv.excel.imports.InfluencerImportExcel;
import com.example.halagodainv.exception.ErrorResponse;
import com.example.halagodainv.exception.GeneralException;
import com.example.halagodainv.model.ClassifyEntity;
import com.example.halagodainv.model.IndustryEntity;
import com.example.halagodainv.model.InfluencerDetailEntity;
import com.example.halagodainv.model.InfluencerEntity;
import com.example.halagodainv.repository.*;
import com.example.halagodainv.request.influencer.InfluencerAddRequest;
import com.example.halagodainv.request.influencer.InfluencerSearch;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.response.PageResponse;
import com.example.halagodainv.service.InfluencerService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class InfluencerServiceImpl implements InfluencerService {
    private final InfluencerEntityRepository influencerEntityRepository;
    private final InfluencerDetailRepository influencerDetailRepository;
    private final InfluencerExcel influencerExcel;
    private final UserRepository userRepository;
    private final IndustryRepository industryRepository;
    private final ClassifyRepository classifyRepository;
    private final InfluencerImportExcel influencerImportExcel;
    private final DownFileImportExcel downFileImportExcel;
    @PersistenceContext
    private final EntityManager entityManager;

    public Object getAll(InfluencerSearch search) {
        int offset = 0;
        if (search.getPageNo() > 0) offset = search.getPageNo() - 1;
        Pageable pageable = PageRequest.of(offset, search.getPageSize());
        Query nativeQuery = entityManager.createNativeQuery(StrSqlQuery(search, offset));
        List<InflucerMenuDto> influcerMenuDtos = nativeQuery.unwrap(NativeQuery.class).setResultTransformer(Transformers.aliasToBean(InflucerMenuDto.class)).getResultList();
        Query nativeQueryCount = entityManager.createNativeQuery(countInfluQuery(search));
        List<InflucerMenuDto> countQuery = nativeQueryCount.unwrap(NativeQuery.class).setResultTransformer(Transformers.aliasToBean(InflucerMenuDto.class)).getResultList();
        PageResponse<?> pageResponse = new PageResponse<>(new PageImpl<>(CollectionUtils.isEmpty(influcerMenuDtos) ? new ArrayList<>() : influcerMenuDtos, pageable, CollectionUtils.isEmpty(countQuery) ? 0 : countQuery.size()));
        return new BaseResponse<>(HttpStatus.OK.value(), "Lấy thành công", pageResponse);
    }

    private static String StrSqlQuery(InfluencerSearch search, int offset) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT DISTINCT ie.id as id ,ie.name as name , " +
                "ie.is_facebook as isFacebook ,ie.is_tiktok as isTikTok,ie.is_instagram as isInstagram,ie.is_youtube as isYouTube," +
                "ie.industry as industryId ,ie.industry_name as industry,ie.phone FROM " +
                "influencer_entity ie left join influencer_detail id on ie.id = id.influ_id " +
                "WHERE  (ie.phone  is not null or ie.phone <> '') and (ie.name is not null or ie.name  <> '') ");
        strSqlQuerySearch(search, stringBuilder);
//        stringBuilder.append(" limit ").append(search.getPageSize()).append(" offset ").append(offset * 10);
        return stringBuilder.toString();
    }

    private static String countInfluQuery(InfluencerSearch search) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT DISTINCT ie.id as id ,ie.name as name , " +
                "ie.is_facebook as isFacebook ,ie.is_tiktok as isTikTok,ie.is_instagram as isInstagram,ie.is_youtube as isYouTube," +
                "ie.industry as industryId ,ie.industry_name as industry,ie.phone FROM " +
                "influencer_entity ie left join influencer_detail id on ie.id = id.influ_id " +
                "WHERE  (ie.phone  is not null or ie.phone <> '') and (ie.name is not null or ie.name  <> '') ");
        strSqlQuerySearch(search, stringBuilder);
        return stringBuilder.toString();
    }

    public Object getSubInflu(InfluencerSearch search) {
        int offset = 0;
        if (search.getPageNo() > 0) offset = search.getPageNo() - 1;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT DISTINCT ie.id as id ,ie.name as name,ie.phone,id.url as link,id.follower as follower," +
                "id.expense as expense,ie.industry as industryId,ie.industry_name as industry FROM " +
                "influencer_entity ie left join influencer_detail id on ie.id = id.influ_id " +
                "WHERE  (ie.phone  is not null or ie.phone <> '') and (ie.name is not null or ie.name  <> '') ");
        strSqlQuerySearch(search, stringBuilder);
        stringBuilder.append(" limit ").append(search.getPageSize()).append(" offset ").append(offset * 10);
        Pageable pageable = PageRequest.of(offset, search.getPageSize());
        Query nativeQuery = entityManager.createNativeQuery(stringBuilder.toString());
        List<InflucerDtoSubMenu> influcerMenuDtos;
        List<InflucerDtoSubMenu> influcerMenuCount = new ArrayList<>();
        if (isCheckBooleanSearch(search.getIsInstagram()) || isCheckBooleanSearch(search.getIsYoutube()) || isCheckBooleanSearch(search.getIsTikTok()) || isCheckBooleanSearch(search.getIsFacebook())) {
            influcerMenuDtos = nativeQuery.unwrap(NativeQuery.class).setResultTransformer(Transformers.aliasToBean(InflucerDtoSubMenu.class)).getResultList();
            StringBuilder countSubInflu = new StringBuilder();
            countSubInflu.append("SELECT DISTINCT ie.id as id ,ie.name as name,ie.phone,id.url as link,id.follower as follower," +
                    "id.expense as expense,ie.industry as industryId,ie.industry_name as industry FROM " +
                    "influencer_entity ie left join influencer_detail id on ie.id = id.influ_id " +
                    "WHERE  (ie.phone  is not null or ie.phone <> '') and (ie.name is not null or ie.name  <> '') ");
            strSqlQuerySearch(search, countSubInflu);
            Query nativeQueryCount = entityManager.createNativeQuery(stringBuilder.toString());
            influcerMenuCount = nativeQueryCount.unwrap(NativeQuery.class).setResultTransformer(Transformers.aliasToBean(InflucerDtoSubMenu.class)).getResultList();
        } else {
            influcerMenuDtos = new ArrayList<>();
        }
        PageResponse<?> pageResponse = new PageResponse<>(new PageImpl<>(CollectionUtils.isEmpty(influcerMenuDtos) ? new ArrayList<>() : influcerMenuDtos, pageable, CollectionUtils.isEmpty(influcerMenuCount) ? 0 : influcerMenuCount.size()));
        return new BaseResponse<>(HttpStatus.OK.value(), "Lấy thành công", pageResponse);
    }


    private static String isStartYear(String value) {
        return StringUtils.isEmpty(value) ? "1900" : value;
    }

    private static String isEndYear(String value) {
        return StringUtils.isEmpty(value) ? "9999" : value;
    }


    private static boolean isCheckBooleanSearch(Boolean value) {
        return Boolean.TRUE.equals(value);
    }

    @Override
    public Object findInfluencerById(long id) {
        try {
            List<InflucerDtoListDetail> influencers = influencerEntityRepository.getDetails(id);
            Set<InfluencerDtoDetails> dtoDetailsSet = new HashSet<>();
            InfluencerDtoDetails dtoDetails = new InfluencerDtoDetails();
            for (InflucerDtoListDetail influencer : influencers) {
                dtoDetails.setId(influencer.getId());
                dtoDetails.setName(influencer.getName());
                dtoDetails.setAddress(influencer.getAddress());
                dtoDetails.setBankId(influencer.getBankId());
                dtoDetails.setClassifyId(parseStringToListOfIntegers(influencer.getClassify()));
                dtoDetails.setBankNumber(influencer.getBankNumber());
                dtoDetails.setBirtYear(influencer.getBirtYear());
                dtoDetails.setSex(influencer.getSex());
                dtoDetails.setEmail(influencer.getEmail());
                dtoDetails.setProvinceId(influencer.getProvinceId());
                dtoDetails.setBirtYear(influencer.getBirtYear());
                dtoDetails.setCreateHistory(DateFormatUtils.format(influencer.getCreateHistory(), "yyyy-MM-dd"));
                dtoDetails.setPhone(influencer.getPhone());
                dtoDetails.setIndustry(parseStringToListOfIntegers(influencer.getIndustry()));
                if ("FACEBOOK".toUpperCase().equals(influencer.getChannel())) {
                    dtoDetails.setLinkFb(influencer.getLink());
                    dtoDetails.setExpenseFb(influencer.getExpense());
                    dtoDetails.setFollowerFb(influencer.getFollower());
                }
                if ("YOUTUBE".toUpperCase().equals(influencer.getChannel())) {
                    dtoDetails.setLinkYT(influencer.getLink());
                    dtoDetails.setExpenseYT(influencer.getExpense());
                    dtoDetails.setFollowerYT(influencer.getFollower());
                }
                if ("TIKTOK".toUpperCase().equals(influencer.getChannel())) {
                    dtoDetails.setLinkTT(influencer.getLink());
                    dtoDetails.setExpenseTT(influencer.getExpense());
                    dtoDetails.setFollowerTT(influencer.getFollower());
                }
                if ("INSTAGRAM".toUpperCase().equals(influencer.getChannel())) {
                    dtoDetails.setLinkIns(influencer.getLink());
                    dtoDetails.setExpenseIns(influencer.getExpense());
                    dtoDetails.setFollowerIns(influencer.getFollower());
                }
            }
            dtoDetailsSet.add(dtoDetails);
            return new BaseResponse<>(HttpStatus.OK.value(), "Tìm thành công", dtoDetailsSet);
        } catch (Exception e) {
            return new ErrorResponse<>(500, "Tìm thất bại", null);
        }
    }

    @Override
    @Transactional
    public Object add(InfluencerAddRequest request) {
        try {
            Optional<InfluencerEntity> isCheckEmail = influencerEntityRepository.findByEmail(request.getEmail());
            if (isCheckEmail.isPresent()) {
                return new ErrorResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Email [" + request.getEmail() + "] này đã tồn tại", null);
            }
            Optional<InfluencerEntity> isCheckPhone = influencerEntityRepository.findByPhone(request.getPhone());
            if (isCheckPhone.isPresent()) {
                return new ErrorResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Số điện thoại [" + request.getPhone() + "] này đã tồn tại", null);
            }
            Optional<InfluencerEntity> isCheckInfluName = influencerEntityRepository.findByInflucerName(request.getName());
//            if (isCheckInfluName.isPresent()) {
//                return new ErrorResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Tên influencer [" + request.getName() + "] này đã tồn tại", null);
//            }
            InfluencerEntity influencer = new InfluencerEntity();
            List<InfluencerDetailEntity> influencerDetailEntities = new ArrayList<>();
            influencer.setInflucerName(request.getName());
            influencer.setHistoryCreated(new Date());
            influencer.setSex(request.getSex());
            influencer.setPhone(request.getPhone());
            influencer.setYearOld(String.valueOf(request.getBirtYear()));
            influencer.setEmail(request.getEmail());
            influencer.setBankId(request.getBankId().toUpperCase());
            influencer.setAccountNumber(request.getBankNumber());
            influencer.setUserId(request.getUserId());
            influencer.setCreated(new Date());
            if (request.getIndustry().size() > 0) {
                influencer.setIndustry(parseListIntegerToString(request.getIndustry()));
                List<IndustryEntity> industryEntities = industryRepository.findByIdIn(request.getIndustry());
                StringJoiner stringJoiner = new StringJoiner(", ");
                industryEntities.forEach(industryEntity -> {
                    stringJoiner.add(industryEntity.getIndustryName());
                });
                influencer.setIndustryName(stringJoiner.toString());
            }
            influencer.setAddress(request.getAddress());
            influencer.setProvinceId(request.getProvinceId());
            if (request.getClassifyId().size() > 0) {
                influencer.setClassifyId(parseListIntegerToString(request.getClassifyId()));
                List<ClassifyEntity> classifyEntities = classifyRepository.findByIdIn(request.getClassifyId());
                StringJoiner stringJoiner = new StringJoiner(", ");
                classifyEntities.forEach(classifyEntity -> {
                    stringJoiner.add(classifyEntity.getName());
                });
                influencer.setClassifyName(stringJoiner.toString());
            }
            if (!StringUtils.isBlank(request.getLinkFb()) || !StringUtils.isBlank(request.getFollowerFb()) || !StringUtils.isBlank(request.getExpenseFb())) {
                influencer.setFacebook(true);
            }
            if (!StringUtils.isBlank(request.getLinkTT()) || !StringUtils.isBlank(request.getFollowerTT()) || !StringUtils.isBlank(request.getExpenseTT())) {
                influencer.setTiktok(true);
            }
            if (!StringUtils.isBlank(request.getLinkYT()) || !StringUtils.isBlank(request.getFollowerYT()) || !StringUtils.isBlank(request.getExpenseYT())) {
                influencer.setYoutube(true);
            }
            if (!StringUtils.isBlank(request.getLinkIns()) || !StringUtils.isBlank(request.getFollowerIns()) || !StringUtils.isBlank(request.getExpenseIns())) {
                influencer.setInstagram(true);
            }
            influencer = influencerEntityRepository.save(influencer);
            if (Boolean.TRUE.equals(influencer.isFacebook())) {
                InfluencerDetailEntity detailEntityFacebook = new InfluencerDetailEntity();
                detailEntityFacebook.setChannel("FACEBOOK".toUpperCase());
                detailEntityFacebook.setFollower(request.getFollowerFb());
                detailEntityFacebook.setExpense(request.getExpenseFb());
                detailEntityFacebook.setUrl(request.getLinkFb());
                detailEntityFacebook.setInfluId(influencer.getId());
                influencerDetailEntities.add(detailEntityFacebook);
            }
            if (Boolean.TRUE.equals(influencer.isTiktok())) {
                InfluencerDetailEntity detailEntityTikTok = new InfluencerDetailEntity();
                detailEntityTikTok.setChannel("TIKTOK".toUpperCase());
                detailEntityTikTok.setFollower(request.getFollowerTT());
                detailEntityTikTok.setExpense(request.getExpenseTT());
                detailEntityTikTok.setUrl(request.getLinkTT());
                detailEntityTikTok.setInfluId(influencer.getId());
                influencerDetailEntities.add(detailEntityTikTok);
            }
            if (Boolean.TRUE.equals(influencer.isYoutube())) {
                InfluencerDetailEntity detailEntityYoutube = new InfluencerDetailEntity();
                detailEntityYoutube.setChannel("YOUTUBE".toUpperCase());
                detailEntityYoutube.setFollower(request.getFollowerYT());
                detailEntityYoutube.setExpense(request.getExpenseYT());
                detailEntityYoutube.setUrl(String.valueOf(request.getLinkYT()));
                detailEntityYoutube.setInfluId(influencer.getId());
                influencerDetailEntities.add(detailEntityYoutube);
            }
            if (Boolean.TRUE.equals(influencer.isInstagram())) {
                InfluencerDetailEntity detailEntityInstagram = new InfluencerDetailEntity();
                detailEntityInstagram.setChannel("INSTAGRAM".toUpperCase());
                detailEntityInstagram.setFollower(request.getFollowerIns());
                detailEntityInstagram.setExpense(request.getExpenseIns());
                detailEntityInstagram.setUrl(String.valueOf(request.getLinkIns()));
                detailEntityInstagram.setInfluId(influencer.getId());
                influencerDetailEntities.add(detailEntityInstagram);
            }
            influencerDetailRepository.saveAll(influencerDetailEntities);
            return new BaseResponse<>(HttpStatus.OK.value(), "Thêm thành công", findInfluencerById(influencer.getId()));
        } catch (Exception e) {
            throw new RuntimeException("Thêm dữ liệu thất bại !");
        }
    }


    public Object edit(InfluencerAddRequest request) {
        try {
            List<InfluencerDetailEntity> influencerDetailEntities = new ArrayList<>();
            Optional<InfluencerEntity> entity = influencerEntityRepository.findById(request.getId());
            if (entity.isPresent()) {
                entity.get().setEmail("");
                entity.get().setPhone("");
                entity.get().setInflucerName("");
                influencerEntityRepository.save(entity.get());
                Optional<InfluencerEntity> isCheckEmail = influencerEntityRepository.findByEmail(request.getEmail());
                if (isCheckEmail.isPresent()) {
                    return new ErrorResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Email [" + request.getEmail() + "] này đã tồn tại", null);
                }
                Optional<InfluencerEntity> isCheckPhone = influencerEntityRepository.findByPhone(request.getPhone());
                if (isCheckPhone.isPresent()) {
                    return new ErrorResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Số điện thoại [" + request.getPhone() + "] này đã tồn tại", null);
                }
//                Optional<InfluencerEntity> isCheckInfluName = influencerEntityRepository.findByInflucerName(request.getName());
//                if (isCheckInfluName.isPresent()) {
//                    return new ErrorResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Tên influencer [" + request.getName() + "] này đã tồn tại", null);
//                }
                entity.get().setInflucerName(request.getName());
                entity.get().setHistoryCreated(new Date());
                entity.get().setSex(request.getSex());
                entity.get().setPhone(request.getPhone());
                entity.get().setYearOld(String.valueOf(request.getBirtYear()));
                entity.get().setEmail(request.getEmail());
                entity.get().setBankId(request.getBankId().toUpperCase());
                entity.get().setAccountNumber(request.getBankNumber());
                if (request.getIndustry().size() > 0) {
                    entity.get().setIndustry(parseListIntegerToString(request.getIndustry()));
                    List<IndustryEntity> industryEntities = industryRepository.findByIdIn(request.getIndustry());
                    StringJoiner stringJoiner = new StringJoiner(", ");
                    industryEntities.forEach(industryEntity -> {
                        stringJoiner.add(industryEntity.getIndustryName());
                    });
                    entity.get().setIndustryName(stringJoiner.toString());
                }
                entity.get().setAddress(request.getAddress());
                entity.get().setProvinceId(request.getProvinceId()

                );
                if (request.getClassifyId().size() > 0) {
                    entity.get().setClassifyId(parseListIntegerToString(request.getClassifyId()));
                    List<ClassifyEntity> classifyEntities = classifyRepository.findByIdIn(request.getClassifyId());
                    StringJoiner stringJoiner = new StringJoiner(", ");
                    classifyEntities.forEach(classifyEntity -> {
                        stringJoiner.add(classifyEntity.getName());
                    });
                    entity.get().setClassifyName(stringJoiner.toString());
                } else {
                    entity.get().setClassifyId("");
                    entity.get().setClassifyName("");
                }
                if (!StringUtils.isBlank(request.getLinkFb()) || !StringUtils.isBlank(request.getFollowerFb()) || !StringUtils.isBlank(request.getExpenseFb())) {
                    entity.get().setFacebook(true);
                } else {
                    entity.get().setFacebook(false);
                }
                if (!StringUtils.isBlank(request.getLinkTT()) || !StringUtils.isBlank(request.getFollowerTT()) || !StringUtils.isBlank(request.getExpenseTT())) {
                    entity.get().setTiktok(true);
                } else {
                    entity.get().setTiktok(false);
                }
                if (!StringUtils.isBlank(request.getLinkYT()) || !StringUtils.isBlank(request.getFollowerYT()) || !StringUtils.isBlank(request.getExpenseYT())) {
                    entity.get().setYoutube(true);
                } else {
                    entity.get().setYoutube(false);
                }
                if (!StringUtils.isBlank(request.getLinkIns()) || !StringUtils.isBlank(request.getFollowerIns()) || !StringUtils.isBlank(request.getExpenseIns())) {
                    entity.get().setInstagram(true);
                } else {
                    entity.get().setInstagram(false);
                }
                influencerEntityRepository.save(entity.get());
                influencerDetailRepository.deleteByInfluId(entity.get().getId());
                if (Boolean.TRUE.equals(entity.get().isFacebook())) {
                    InfluencerDetailEntity detailEntityFacebook = new InfluencerDetailEntity();
                    detailEntityFacebook.setChannel("FACEBOOK".toUpperCase());
                    detailEntityFacebook.setFollower(request.getFollowerFb());
                    detailEntityFacebook.setExpense(request.getExpenseFb());
                    detailEntityFacebook.setUrl(request.getLinkFb());
                    detailEntityFacebook.setInfluId(entity.get().getId());
                    influencerDetailEntities.add(detailEntityFacebook);
                }
                if (Boolean.TRUE.equals(entity.get().isTiktok())) {
                    InfluencerDetailEntity detailEntityTikTok = new InfluencerDetailEntity();
                    detailEntityTikTok.setChannel("TIKTOK".toUpperCase());
                    detailEntityTikTok.setFollower(request.getFollowerTT());
                    detailEntityTikTok.setExpense(request.getExpenseTT());
                    detailEntityTikTok.setUrl(request.getLinkTT());
                    detailEntityTikTok.setInfluId(entity.get().getId());
                    influencerDetailEntities.add(detailEntityTikTok);
                }
                if (Boolean.TRUE.equals(entity.get().isYoutube())) {
                    InfluencerDetailEntity detailEntityYoutube = new InfluencerDetailEntity();
                    detailEntityYoutube.setChannel("YOUTUBE".toUpperCase());
                    detailEntityYoutube.setFollower(request.getFollowerYT());
                    detailEntityYoutube.setExpense(request.getExpenseYT());
                    detailEntityYoutube.setUrl(String.valueOf(request.getLinkYT()));
                    detailEntityYoutube.setInfluId(entity.get().getId());
                    influencerDetailEntities.add(detailEntityYoutube);
                }
                if (Boolean.TRUE.equals(entity.get().isInstagram())) {
                    InfluencerDetailEntity detailEntityInstagram = new InfluencerDetailEntity();
                    detailEntityInstagram.setChannel("INSTAGRAM".toUpperCase());
                    detailEntityInstagram.setFollower(request.getFollowerIns());
                    detailEntityInstagram.setExpense(request.getExpenseIns());
                    detailEntityInstagram.setUrl(String.valueOf(request.getLinkIns()));
                    detailEntityInstagram.setInfluId(entity.get().getId());
                    influencerDetailEntities.add(detailEntityInstagram);
                }
                influencerDetailRepository.saveAll(influencerDetailEntities);
                return new BaseResponse<>(HttpStatus.OK.value(), "Sửa thành công", findInfluencerById(entity.get().getId()));
            }
            return new ErrorResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Dữ liệu không tồn tại!", null);
        } catch (Exception e) {
            return new ErrorResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Sửa liệu thất bại !", null);
        }
    }

    @Override
    @Transactional
    @Modifying
    public Object delete(long id) {
        try {
            Optional<InfluencerEntity> influencer = influencerEntityRepository.findById(id);
            if (!influencer.isPresent()) {
                return new BaseResponse(Constant.FAILED, "Xóa  thất bại", new BaseResponse(0, "Xóa  thất bại", null));
            }
            influencerDetailRepository.deleteByInfluId(id);
            influencerEntityRepository.deleteById(id);
            if (influencer.get().getUserId() != null) {
                userRepository.deleteById(influencer.get().getUserId());
            }
            return new BaseResponse(Constant.SUCCESS, "Xóa  thành công", new BaseResponse(1, "Xóa  thành công", null));
        } catch (Exception e) {
            return new BaseResponse(Constant.FAILED, "Xóa  thất bại", new BaseResponse(0, "Xóa  thất bại", null));

        }
    }

    public byte[] exportExcel(InfluencerSearch search) {
        try {
            List<InfluencerExportExcelDto> exportAllData = getExportExcel(search);
            influencerExcel.initializeData(exportAllData, "template/Influencer.xls");
            return influencerExcel.export();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<InfluencerExportExcelDto> getExportExcel(InfluencerSearch search) {
        Query nativeQuery = entityManager.createNativeQuery(StrQueryExportExcel(search));
        List<InfluencerExportExcelDto> influencerExportExcelDtos = nativeQuery.unwrap(NativeQuery.class).setResultTransformer(Transformers.aliasToBean(InfluencerExportExcelDto.class)).getResultList();
        if (CollectionUtils.isEmpty(influencerExportExcelDtos)) {
            return new ArrayList<>();
        }
        return influencerExportExcelDtos;
    }

    private static String StrQueryExportExcel(InfluencerSearch search) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT DISTINCT ie.id ,ie.name, " +
                "(CASE " +
                "    WHEN ie.sex = 1 THEN 'Nam'\n" +
                "    WHEN ie.sex = 2 THEN 'Nữ'\n" +
                "    ELSE 'LGBT'\n" +
                "  END) as sex ,ie.year_old as dateOfBirth ,\n" +
                "(SELECT id1.follower from influencer_detail id1 WHERE id1.influ_id = ie.id and id1.channel ='FACEBOOK') as followerFacebook," +
                "(SELECT id1.expense from influencer_detail id1 WHERE id1.influ_id = ie.id and id1.channel ='FACEBOOK') as expenseFacebook," +
                "(SELECT id1.url from influencer_detail id1 WHERE id1.influ_id = ie.id and id1.channel ='FACEBOOK') as linkFacebook," +
                "(SELECT id1.follower from influencer_detail id1 WHERE id1.influ_id = ie.id and id1.channel ='TIKTOK') as followerTiktok," +
                "(SELECT id1.expense from influencer_detail id1 WHERE id1.influ_id = ie.id and id1.channel ='TIKTOK') as expenseTiktok," +
                "(SELECT id1.url from influencer_detail id1 WHERE id1.influ_id = ie.id and id1.channel ='TIKTOK') as linkTiktok," +
                "(SELECT id1.follower from influencer_detail id1 WHERE id1.influ_id = ie.id and id1.channel ='INSTAGRAM') as followerInstagram," +
                "(SELECT id1.expense from influencer_detail id1 WHERE id1.influ_id = ie.id and id1.channel ='INSTAGRAM') as expenseInstagram," +
                "(SELECT id1.url from influencer_detail id1 WHERE id1.influ_id = ie.id and id1.channel ='INSTAGRAM') as linkInstagram," +
                "(SELECT id1.follower from influencer_detail id1 WHERE id1.influ_id = ie.id and id1.channel ='YOUTUBE') as followerYoutube," +
                "(SELECT id1.expense from influencer_detail id1 WHERE id1.influ_id = ie.id and id1.channel ='YOUTUBE') as expenseYoutube," +
                "(SELECT id1.url from influencer_detail id1 WHERE id1.influ_id = ie.id and id1.channel ='YOUTUBE') as linkYoutube," +
                "ie.address ,ie.industry_name as industry ,ie.classify_name as classify,ie.phone \n" +
                "FROM influencer_entity ie \n" +
                "left join influencer_detail id on ie.id = id.influ_id\n" +
                "WHERE  (ie.phone  is not null or ie.phone <> '') and (ie.name is not null or ie.name  <> '') ");
        if (search.getIds().size() > 0) {
            stringBuilder.append(" and ie.id in(").append(InfluencerServiceImpl.parseListIntegerToString(search.getIds())).append(")");
        }
        strSqlQuerySearch(search, stringBuilder);
        return stringBuilder.toString();
    }

    @Override
    public void importExcel(MultipartFile file) throws GeneralException, IOException {
        influencerImportExcel.ImportFileExcel(file);
    }

    @Override
    public byte[] downFileImportExcel() {
        try {
            downFileImportExcel.initializeData("template/InfluencerImport.xlsx");
            return downFileImportExcel.export();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static List<Integer> parseStringToListOfIntegers(String input) {
        List<Integer> integerList = new ArrayList<>();
        if (!StringUtils.isBlank(input)) {
            String[] numberStrings = input.split(",");
            for (String numberString : numberStrings) {
                int number = Integer.parseInt(numberString.trim());
                integerList.add(number);
            }
            return integerList;
        }
        return new ArrayList<>();
    }


    public static String parseListIntegerToString(List<Integer> inputs) {
        if (inputs.size() > 0) {
            StringJoiner joiner = new StringJoiner(", ");
            for (Integer integer : inputs) {
                joiner.add(String.valueOf(integer).trim());
            }
            return joiner.toString();
        }
        return "";
    }

    private static StringBuilder strSqlQuerySearch(InfluencerSearch search, StringBuilder stringBuilder) {
        stringBuilder.append(" and ie.name like '%").append(search.getName()).append("%'");
        if (isCheckBooleanSearch(search.getIsFacebook())) {
            stringBuilder.append(" and ie.is_facebook = ").append(search.getIsFacebook()).append(" and id.channel ='FACEBOOK'");
        }
        if (isCheckBooleanSearch(search.getIsInstagram())) {
            stringBuilder.append(" and ie.is_instagram = ").append(search.getIsInstagram()).append(" and id.channel ='INSTAGRAM'");
        }
        if (isCheckBooleanSearch(search.getIsYoutube())) {
            stringBuilder.append(" and ie.is_youtube = ").append(search.getIsYoutube()).append(" and id.channel ='YOUTUBE'");
        }
        if (isCheckBooleanSearch(search.getIsTikTok())) {
            stringBuilder.append(" and ie.is_tiktok = ").append(search.getIsTikTok()).append(" and id.channel ='TIKTOK'");
        }

        if (!StringUtils.isEmpty(search.getIndustry())) {
            stringBuilder.append(" and (ie.industry LIKE '").append(search.getIndustry())
                    .append(",%' OR ie.industry LIKE '%, ").append(search.getIndustry())
                    .append(",%' OR ie.industry LIKE '%, ").append(search.getIndustry())
                    .append("' OR ie.industry like ").append("'%").append(search.getIndustry()).append("%')");
        }
        if (!StringUtils.isEmpty(search.getSex())) {
            stringBuilder.append(" and ie.sex = ").append(search.getSex());
        }
        String startYear = isStartYear(search.getStartYear());
        String endYear = isEndYear(search.getEndYear());
        stringBuilder.append(" and (IFNULL(ie.year_old,'') between concat('").append(startYear).append("','-00','-01') and concat('").append(endYear).append("','-31','-12'))");
        if (!StringUtils.isEmpty(search.getStartExpanse()) && !StringUtils.isEmpty(search.getEndExpanse())) {
            stringBuilder.append(" and (IFNULL(id.expense,'') between ").append(search.getStartExpanse().trim()).append(" and ").append(search.getEndExpanse().trim()).append(")");
        } else if (!StringUtils.isEmpty(search.getStartExpanse())) {
            stringBuilder.append(" and (IFNULL(id.expense,'') between ").append(search.getStartExpanse().trim()).append(" and ").append("999999999999999999999").append(")");
        } else if (!StringUtils.isEmpty(search.getEndExpanse())) {
            stringBuilder.append(" and (IFNULL(id.expense,'') between ").append(" 0 ").append("and").append(search.getEndExpanse().trim()).append(")");
        }
        if (!StringUtils.isEmpty(search.getStartFollower()) && !StringUtils.isEmpty(search.getEndFollower())) {
            stringBuilder.append(" and (IFNULL(id.follower,'') between ").append(search.getStartFollower().trim()).append(" and ").append(search.getEndFollower().trim()).append(")");
        } else if (!StringUtils.isEmpty(search.getStartExpanse())) {
            stringBuilder.append(" and (IFNULL(id.follower,'') between ").append(search.getStartFollower().trim()).append(" and ").append("999999999999999999999").append(")");
        } else if (!StringUtils.isEmpty(search.getEndFollower())) {
            stringBuilder.append(" and (IFNULL(id.follower,'') between ").append(" 0 ").append(" and ").append(search.getEndFollower().trim()).append(")");
        }
        stringBuilder.append(" and ((year(CURRENT_DATE()) - COALESCE(SUBSTRING(ie.year_old, 1, 4), 1999)) BETWEEN ").append(search.getAgeStart()).append(" and ").append(search.getAgeEnd()).append(")");
        stringBuilder.append(" order by ie.id desc ");
        return stringBuilder;
    }

    public boolean isCheckInforInflu(String email) {
        return influencerEntityRepository.findByEmail(email).isPresent();
    }
}
