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
import com.example.halagodainv.until.ConvertString;
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
        Query nativeQuery = entityManager.createNativeQuery(StrSqlQuery(search));
        nativeQuery.unwrap(NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(InflucerMenuDto.class));
//                .setFirstResult((int) pageable.getOffset())
//                .setMaxResults(pageable.getPageSize());
        List<InflucerMenuDto> influcerMenuDtos = nativeQuery.getResultList();
        List<InflucerMenuDto> listCount = entityManager.createNativeQuery(countInfluQuery(search)).getResultList();
        int totalCount = CollectionUtils.isEmpty(listCount) ? 0 : listCount.size();
        PageResponse<?> pageResponse = new PageResponse<>(new PageImpl<>(influcerMenuDtos, pageable, totalCount));
        return new BaseResponse<>(HttpStatus.OK.value(), "Lấy thành công", pageResponse);
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
            InfluencerDtoDetails dtoDetails = InfluencerDtoDetails.builder()
                    .id(influencers.get(0).getId())
                    .name(influencers.get(0).getName())
                    .address(influencers.get(0).getAddress())
                    .bankId(influencers.get(0).getBankId())
                    .classifyId(ConvertString.parseStringToListOfIntegers(influencers.get(0).getClassify()))
                    .bankNumber(influencers.get(0).getBankNumber())
                    .birtYear(influencers.get(0).getBirtYear())
                    .sex(influencers.get(0).getSex())
                    .email(influencers.get(0).getEmail())
                    .provinceId(influencers.get(0).getProvinceId())
                    .birtYear(influencers.get(0).getBirtYear())
                    .createHistory(DateFormatUtils.format(influencers.get(0).getCreateHistory(), "yyyy-MM-dd"))
                    .phone(influencers.get(0).getPhone())
                    .industry(ConvertString.parseStringToListOfIntegers(influencers.get(0).getIndustry())).build();
            setSocial(dtoDetails, influencers);
            return new BaseResponse<>(HttpStatus.OK.value(), "Tìm thành công", dtoDetails);
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
            InfluencerEntity influencer = save(request);
            influencer = influencerEntityRepository.save(influencer);
            //add detail
            List<InfluencerDetailEntity> influencerDetailEntities = new ArrayList<>();
            if (Boolean.TRUE.equals(influencer.isFacebook())) {
                influencerDetailEntities.add(saveDetail(influencer, request, "FACEBOOK"));
            }
            if (Boolean.TRUE.equals(influencer.isTiktok())) {
                influencerDetailEntities.add(saveDetail(influencer, request, "TIKTOK"));
            }
            if (Boolean.TRUE.equals(influencer.isYoutube())) {
                influencerDetailEntities.add(saveDetail(influencer, request, "YOUTUBE"));
            }
            if (Boolean.TRUE.equals(influencer.isInstagram())) {
                influencerDetailEntities.add(saveDetail(influencer, request, "INSTAGRAM"));
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
                influencerEntityRepository.save(save(request));
                influencerDetailRepository.deleteByInfluId(entity.get().getId());
                if (Boolean.TRUE.equals(entity.get().isFacebook())) {
                    influencerDetailEntities.add(saveDetail(entity.get(), request, "FACEBOOK"));
                }
                if (Boolean.TRUE.equals(entity.get().isTiktok())) {
                    influencerDetailEntities.add(saveDetail(entity.get(), request, "TIKTOK"));
                }
                if (Boolean.TRUE.equals(entity.get().isYoutube())) {
                    influencerDetailEntities.add(saveDetail(entity.get(), request, "YOUTUBE"));
                }
                if (Boolean.TRUE.equals(entity.get().isInstagram())) {
                    influencerDetailEntities.add(saveDetail(entity.get(), request, "INSTAGRAM"));
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
            if (influencer.isEmpty()) {
                return new ErrorResponse<>(Constant.FAILED, "Xóa  thất bại", new ErrorResponse<>(0, "Xóa  thất bại", null));
            }
            influencerDetailRepository.deleteByInfluId(id);
            influencerEntityRepository.deleteById(id);
            if (influencer.get().getUserId() != null && influencer.get().getUserId() > 0) {
                userRepository.deleteById(influencer.get().getUserId());
            }
            return new BaseResponse<>(Constant.SUCCESS, "Xóa  thành công", new BaseResponse<>(1, "Xóa  thành công", null));
        } catch (Exception e) {
            return new ErrorResponse<>(Constant.FAILED, "Xóa  thất bại", new ErrorResponse<>(0, "Xóa  thất bại", null));

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
            stringBuilder.append(" and ie.id in(").append(ConvertString.parseListIntegerToString(search.getIds())).append(")");
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

    public boolean isCheckInforInflu(String email) {
        return influencerEntityRepository.findByEmail(email).isPresent();
    }

    private String getIndustryName(InfluencerAddRequest request) {
        if (!request.getIndustry().isEmpty()) {
            List<IndustryEntity> industryEntities = industryRepository.findByIdIn(request.getIndustry());
            StringJoiner stringJoiner = new StringJoiner(", ");
            industryEntities.forEach(industryEntity -> {
                stringJoiner.add(industryEntity.getIndustryName());
            });
            return stringJoiner.toString();
        }
        return "";
    }

    private String getClassify(InfluencerAddRequest request) {
        if (!request.getClassifyId().isEmpty()) {
            List<ClassifyEntity> classifyEntities = classifyRepository.findByIdIn(request.getClassifyId());
            StringJoiner stringJoiner = new StringJoiner(", ");
            classifyEntities.forEach(classifyEntity -> {
                stringJoiner.add(classifyEntity.getName());
            });
            return stringJoiner.toString();
        }
        return "";
    }

    private InfluencerEntity save(InfluencerAddRequest request) {
        InfluencerEntity influencer = new InfluencerEntity();
        if (request.getId() > 0) {
            influencer.setId(request.getId());
        }
        influencer = InfluencerEntity.builder()
                .influcerName(request.getName())
                .historyCreated(new Date())
                .sex(request.getSex())
                .phone(request.getPhone())
                .yearOld(String.valueOf(request.getBirtYear()))
                .email(request.getEmail())
                .bankId(request.getBankId().toUpperCase())
                .accountNumber(request.getBankNumber())
                .userId(request.getUserId())
                .created(new Date())
                .industry(ConvertString.parseListIntegerToString(request.getIndustry()))
                .industryName(getIndustryName(request))
                .address(request.getAddress())
                .provinceId(request.getProvinceId())
                .classifyId(ConvertString.parseListIntegerToString(request.getClassifyId()))
                .classifyName(getClassify(request))
                .isFacebook(!StringUtils.isBlank(request.getLinkFb()) || !StringUtils.isBlank(request.getFollowerFb()) || !StringUtils.isBlank(request.getExpenseFb()))
                .isTiktok(!StringUtils.isBlank(request.getLinkTT()) || !StringUtils.isBlank(request.getFollowerTT()) || !StringUtils.isBlank(request.getExpenseTT()))
                .isYoutube(!StringUtils.isBlank(request.getLinkYT()) || !StringUtils.isBlank(request.getFollowerYT()) || !StringUtils.isBlank(request.getExpenseYT()))
                .isInstagram(!StringUtils.isBlank(request.getLinkIns()) || !StringUtils.isBlank(request.getFollowerIns()) || !StringUtils.isBlank(request.getExpenseIns()))
                .build();
        return influencer;
    }

    private InfluencerDetailEntity saveDetail(InfluencerEntity influencer, InfluencerAddRequest request, String socialNetwork) {
        InfluencerDetailEntity addDetail = new InfluencerDetailEntity();
        if (socialNetwork.equalsIgnoreCase("FACEBOOK")) {
            addDetail = InfluencerDetailEntity
                    .builder()
                    .channel("FACEBOOK".toUpperCase())
                    .follower(request.getFollowerFb())
                    .expense(request.getExpenseFb())
                    .url(request.getLinkFb())
                    .influId(influencer.getId()).build();
        } else if (socialNetwork.equalsIgnoreCase("YOUTUBE")) {
            addDetail = InfluencerDetailEntity
                    .builder()
                    .channel("YOUTUBE".toUpperCase())
                    .follower(request.getFollowerYT())
                    .expense(request.getExpenseYT())
                    .url(request.getLinkYT())
                    .influId(influencer.getId()).build();
        } else if (socialNetwork.equalsIgnoreCase("INSTAGRAM")) {
            addDetail = InfluencerDetailEntity
                    .builder()
                    .channel("INSTAGRAM".toUpperCase())
                    .follower(request.getFollowerIns())
                    .expense(request.getExpenseIns())
                    .url(request.getLinkIns())
                    .influId(influencer.getId()).build();
        }
        return addDetail;
    }

    private void setSocial(InfluencerDtoDetails dtoDetails, List<InflucerDtoListDetail> influencers) {
        for (InflucerDtoListDetail influencer : influencers) {
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
    }


    private static String StrSqlQuery(InfluencerSearch search) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT DISTINCT ie.id as id ,ie.name as name , " +
                "ie.is_facebook as isFacebook ,ie.is_tiktok as isTikTok,ie.is_instagram as isInstagram,ie.is_youtube as isYouTube," +
                "ie.industry as industryId ,ie.industry_name as industry,ie.phone FROM " +
                "influencer_entity ie left join influencer_detail id on ie.id = id.influ_id " +
                "WHERE  (ie.phone  is not null or ie.phone <> '') and (ie.name is not null or ie.name  <> '') ");
        strSqlQuerySearch(search, stringBuilder);
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

    private static StringBuilder strSqlQuerySearch(InfluencerSearch search, StringBuilder stringBuilder) {
        stringBuilder.append(" and ie.name like '%").append(search.getName()).append("%'");
        if (search.getId() != null) {
            stringBuilder.append(" and ie.id like '%").append(search.getId()).append("%'");
        }
        if (!StringUtils.isEmpty(search.getPhoneNumber())) {
            stringBuilder.append(" and ie.phone like '%").append(search.getPhoneNumber()).append("%'");
        }
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
}
