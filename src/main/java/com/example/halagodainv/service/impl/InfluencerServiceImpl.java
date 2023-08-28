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
import com.example.halagodainv.request.excel.InfluceRequestExportExcel;
import com.example.halagodainv.request.influencer.InfluencerAddRequest;
import com.example.halagodainv.request.influencer.InfluencerSearch;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.response.PageResponse;
import com.example.halagodainv.service.InfluencerService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class InfluencerServiceImpl implements InfluencerService {
    private final InfluencerEntityRepository influencerEntityRepository;
    private final InfluencerDetailRepository influencerDetailRepository;
    private final InfluencerExcel influencerExcel;
    private final IndustryRepository industryRepository;
    private final ClassifyRepository classifyRepository;
    private final InfluencerImportExcel influencerImportExcel;
    private final DownFileImportExcel downFileImportExcel;

    @Override
    public Object getInfluMenu(InfluencerSearch search) {
        try {
            int offset = 0;
            if (search.getPageNo() > 0) offset = search.getPageNo() - 1;
            Boolean isFB = search.getIsFacebook() != null ? search.getIsFacebook() : null;
            Boolean isIns = search.getIsInstagram() != null ? search.getIsInstagram() : null;
            Boolean isTT = search.getIsTikTok() != null ? search.getIsTikTok() : null;
            Boolean isYT = search.getIsYoutube() != null ? search.getIsYoutube() : null;
            Pageable pageable = PageRequest.of(offset, search.getPageSize(), Sort.Direction.DESC, "id");
            PageResponse pageResponse;
            int proviceId = Strings.isBlank(search.getProvinceId()) ? 0 : Integer.valueOf(search.getProvinceId()).intValue();
            int sexId = Strings.isBlank(search.getSex()) ? 0 : Integer.valueOf(search.getSex()).intValue();
            if (Strings.isBlank(search.getExpanse()) && Strings.isBlank(search.getFollower())) {
                long total = influencerEntityRepository.totalCount(isFB, isYT, isIns, isTT, search.getIndustry(), proviceId, sexId, search.getBirhYear());
                List<InflucerMenuDto> influcerMenuDtos = influencerEntityRepository.getAll(isFB, isYT, isIns, isTT, search.getIndustry(), proviceId, sexId, search.getBirhYear(), pageable);
                if (CollectionUtils.isEmpty(influcerMenuDtos)) {
                    pageResponse = new PageResponse<>(new PageImpl<>(influcerMenuDtos, pageable, 0));
                    return new BaseResponse(HttpStatus.OK.value(), "Lấy thành công", pageResponse);
                }
                pageResponse = new PageResponse<>(new PageImpl<>(influcerMenuDtos, pageable, total));
                return new BaseResponse(HttpStatus.OK.value(), "Lấy thành công", pageResponse);
            } else {
                long total = influencerEntityRepository.countFilterMenu(isFB, isYT, isIns, isTT, search.getIndustry(), proviceId, search.getExpanse(), search.getFollower(), sexId, search.getBirhYear());
                List<InflucerMenuDto> filterMenu = influencerEntityRepository.getFilterMenu(isFB, isYT, isIns, isTT, search.getIndustry(), proviceId, search.getExpanse(), search.getFollower(), sexId, search.getBirhYear(), pageable);
                Set<InflucerMenuDto> menuDtoSet = new HashSet<>();
                menuDtoSet.addAll(filterMenu);
                if (CollectionUtils.isEmpty(filterMenu)) {
                    pageResponse = new PageResponse<>(new PageImpl<>(Arrays.asList(menuDtoSet.toArray()), pageable, 0));
                    return new BaseResponse(HttpStatus.OK.value(), "Lấy thành công", pageResponse);
                }
                Set<InflucerMenuDto> sortedMenuDtoSet = new TreeSet<>(Comparator.comparing(InflucerMenuDto::getId).reversed());
                sortedMenuDtoSet.addAll(menuDtoSet);
                pageResponse = new PageResponse<>(new PageImpl<>(Arrays.asList(sortedMenuDtoSet.toArray()), pageable, total));
                return new BaseResponse(HttpStatus.OK.value(), "Lấy thành công", pageResponse);
            }
        } catch (Exception e) {
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Lấy thất bai", null);
        }

    }

    public Object getInfluSubMenu(InfluencerSearch search) {
        try {
            int offset = 0;
            if (search.getPageNo() > 0) offset = search.getPageNo() - 1;
            Boolean isFB = search.getIsFacebook() != null ? search.getIsFacebook() : null;
            Boolean isIns = search.getIsInstagram() != null ? search.getIsInstagram() : null;
            Boolean isTT = search.getIsTikTok() != null ? search.getIsTikTok() : null;
            Boolean isYT = search.getIsYoutube() != null ? search.getIsYoutube() : null;
            Pageable pageable = PageRequest.of(offset, search.getPageSize(), Sort.Direction.DESC, "id");
            int proviceId = Strings.isBlank(search.getProvinceId()) ? 0 : Integer.valueOf(search.getProvinceId()).intValue();
            int sexId = Strings.isBlank(search.getSex()) ? 0 : Integer.valueOf(search.getSex()).intValue();
            long total = influencerEntityRepository.countSubMenu(isFB, isYT, isIns, isTT, search.getIndustry(), search.getExpanse(), search.getFollower(), proviceId, sexId, search.getBirhYear());
            List<InflucerDtoSubMenu> influcerDtoSubMenus = influencerEntityRepository.getSubMenu(isFB, isYT, isIns, isTT, search.getIndustry(), search.getExpanse(), search.getFollower(), proviceId, sexId, search.getBirhYear(), pageable);
            if (CollectionUtils.isEmpty(influcerDtoSubMenus)) {
                PageResponse pageResponse = new PageResponse<>(new PageImpl<>(influcerDtoSubMenus, pageable, 0));
                return new BaseResponse(HttpStatus.OK.value(), "Lấy thành công", pageResponse);
            }
            PageResponse pageResponse = new PageResponse<>(new PageImpl<>(influcerDtoSubMenus, pageable, total));
            return new BaseResponse(HttpStatus.OK.value(), "Lấy thành công", pageResponse);
        } catch (Exception e) {
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Lấy thất bai", null);
        }
    }

    @Override
    public Object findInfluencerById(long id) {
        try {
            List<InflucerDtoListDetail> influencers = influencerEntityRepository.getDetails(id);
            Set<InfluencerDtoDetails> dtoDetailsSet = new HashSet<>();
            InfluencerDtoDetails dtoDetails = new InfluencerDtoDetails();
            influencers.forEach(
                    influencer -> {
                        dtoDetails.setId(influencer.getId());
                        dtoDetails.setName(influencer.getName());
                        dtoDetails.setAddress(influencer.getAddress());
                        dtoDetails.setBankId(influencer.getBankId().toUpperCase());
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
                    });
            dtoDetailsSet.add(dtoDetails);
            return new BaseResponse<>(HttpStatus.OK.value(), "Tìm thành công", dtoDetailsSet);
        } catch (Exception e) {
            return new ErrorResponse(Constant.FAILED, "Tìm thất bại", null);
        }
    }

    @Override
    @Transactional
    public Object add(InfluencerAddRequest request) {
        try {
            Optional<InfluencerEntity> isCheckEmail = influencerEntityRepository.findByEmail(request.getEmail());
            if (isCheckEmail.isPresent()) {
                return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Email đã tồn tại! ", Collections.singletonList(request.getEmail()));
            }
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
            if (!Strings.isBlank(request.getFollowerFb()) || !Strings.isBlank(request.getExpenseFb())) {
                influencer.setFacebook(true);
            }
            if (!Strings.isBlank(request.getFollowerTT()) || !Strings.isBlank(request.getExpenseTT())) {
                influencer.setTiktok(true);
            }
            if (!Strings.isBlank(request.getFollowerYT()) || !Strings.isBlank(request.getExpenseYT())) {
                influencer.setYoutube(true);
            }
            if (!Strings.isBlank(request.getFollowerIns()) || !Strings.isBlank(request.getExpenseIns())) {
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
            return new ErrorResponse(Constant.FAILED, "Thêm thất bại", null);
        }
    }


    public Object edit(InfluencerAddRequest request) {
        try {
            List<InfluencerDetailEntity> influencerDetailEntities = new ArrayList<>();
            Optional<InfluencerEntity> entity = influencerEntityRepository.findById(request.getId());
            if (entity.isPresent()) {
                entity.get().setEmail("");
                influencerEntityRepository.save(entity.get());
                Optional<InfluencerEntity> isCheckEmail = influencerEntityRepository.findByEmail(request.getEmail());
                if (isCheckEmail.isPresent()) {
                    return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Email đã tồn tại!", Collections.singletonList(request.getEmail()));
                }
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
                if (!Strings.isBlank(request.getFollowerFb()) || !Strings.isBlank(request.getExpenseFb())) {
                    entity.get().setFacebook(true);
                } else {
                    entity.get().setFacebook(false);
                }
                if (!Strings.isBlank(request.getFollowerTT()) || !Strings.isBlank(request.getExpenseTT())) {
                    entity.get().setTiktok(true);
                } else {
                    entity.get().setTiktok(false);
                }
                if (!Strings.isBlank(request.getFollowerYT()) || !Strings.isBlank(request.getExpenseYT())) {
                    entity.get().setYoutube(true);
                } else {
                    entity.get().setYoutube(false);
                }
                if (!Strings.isBlank(request.getFollowerIns()) || !Strings.isBlank(request.getExpenseIns())) {
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
            return new ErrorResponse(HttpStatus.NO_CONTENT.value(), "Dữ liệu không tồn tại!", null);
        } catch (Exception e) {
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error server", Collections.singletonList(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
        }
    }

    @Override
    @Transactional
    @Modifying
    public Object delete(long id) {
        try {
            influencerDetailRepository.deleteByInfluId(id);
            influencerEntityRepository.deleteById(id);
            return new BaseResponse(Constant.SUCCESS, "Xóa  thành công", new BaseResponse(1, "Xóa  thành công", null));
        } catch (Exception e) {
            return new BaseResponse(Constant.FAILED, "Xóa  thất bại", new BaseResponse(0, "Xóa  thất bại", null));

        }
    }

    public byte[] exportExcel(InfluceRequestExportExcel search) {
        try {
            List<InfluencerExportExcelDto> facebooks = influencerEntityRepository.getExportExcel(true, null, null, null, search.getIndustry(), search.getExpanse(), search.getFollower(), search.getProvinceId(), search.getSex(), search.getBirhYear());
            List<InfluencerExportExcelDto> tiktoks = influencerEntityRepository.getExportExcel(null, null, null, true, search.getIndustry(), search.getExpanse(), search.getFollower(), search.getProvinceId(), search.getSex(), search.getBirhYear());
            List<InfluencerExportExcelDto> instagrams = influencerEntityRepository.getExportExcel(null, null, true, null, search.getIndustry(), search.getExpanse(), search.getFollower(), search.getProvinceId(), search.getSex(), search.getBirhYear());
            List<InfluencerExportExcelDto> youtubes = influencerEntityRepository.getExportExcel(null, true, null, null, search.getIndustry(), search.getExpanse(), search.getFollower(), search.getProvinceId(), search.getSex(), search.getBirhYear());
            influencerExcel.initializeData(facebooks, tiktoks, instagrams, youtubes, "template/Influencer.xls");
            return influencerExcel.export();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
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
        if (!Strings.isBlank(input)) {
            String[] numberStrings = input.split(",");
            for (String numberString : numberStrings) {
                int number = Integer.parseInt(numberString.trim());
                integerList.add(number);
            }
        }
        return integerList;
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

    public static String parseStringToString(String inputs) {
        List<Integer> integerList = new ArrayList<>();
        if (!Strings.isBlank(inputs)) {
            String[] numberStrings = inputs.split(",");
            for (String numberString : numberStrings) {
                int number = Integer.parseInt(numberString.trim());
                integerList.add(number);
            }
        }
        StringJoiner joiner = new StringJoiner(", ");
        for (Integer integer : integerList) {
            joiner.add(String.valueOf(integer).trim());
        }
        return joiner.toString();
    }
}
