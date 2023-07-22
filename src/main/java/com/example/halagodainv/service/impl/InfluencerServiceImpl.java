package com.example.halagodainv.service.impl;


import com.example.halagodainv.config.Constant;
import com.example.halagodainv.dto.influcer.*;
import com.example.halagodainv.exception.ErrorResponse;
import com.example.halagodainv.model.CityEntity;
import com.example.halagodainv.model.InfluencerDetailEntity;
import com.example.halagodainv.model.InfluencerEntity;
import com.example.halagodainv.repository.*;
import com.example.halagodainv.request.influencer.InFluencerSubMenuSearch;
import com.example.halagodainv.request.influencer.InfluencerAddRequest;
import com.example.halagodainv.request.influencer.InfluencerSearch;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.response.PageResponse;
import com.example.halagodainv.service.InfluencerService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import utils.StringUtils;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class InfluencerServiceImpl implements InfluencerService {
    private final InfluencerEntityRepository influencerEntityRepository;
    private final InfluencerDetailRepository influencerDetailRepository;

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
            if (search.getExpanse() == "") {
                long total = influencerEntityRepository.totalCount(isFB, isYT, isIns, isTT, search.getIndustry(), search.getProvinceId());
                List<InflucerMenuDto> influcerMenuDtos = influencerEntityRepository.getAll(isFB, isYT, isIns, isTT, search.getIndustry(), search.getProvinceId(), pageable);
                if (CollectionUtils.isEmpty(influcerMenuDtos)) {
                    PageResponse pageResponse = new PageResponse<>(new PageImpl<>(influcerMenuDtos, pageable, 0));
                    return new BaseResponse(HttpStatus.OK.value(), "Lấy thành công", pageResponse);
                }
                PageResponse pageResponse = new PageResponse<>(new PageImpl<>(influcerMenuDtos, pageable, total));
                return new BaseResponse(HttpStatus.OK.value(), "Lấy thành công", pageResponse);
            }else {
                long total = influencerEntityRepository.countFilterMenu(isFB, isYT, isIns, isTT, search.getIndustry(), search.getProvinceId(), search.getExpanse(), search.getFollower());
                List<InflucerMenuDto> filterMenu = influencerEntityRepository.getFilterMenu(isFB, isYT, isIns, isTT, search.getIndustry(), search.getProvinceId(), search.getExpanse(), search.getFollower(), pageable);
                Set<InflucerMenuDto> menuDtoSet = new HashSet<>();
                menuDtoSet.addAll(filterMenu);
                if (CollectionUtils.isEmpty(filterMenu)) {
                    PageResponse pageResponse = new PageResponse<>(new PageImpl<>(Arrays.asList(menuDtoSet.toArray()), pageable, 0));
                    return new BaseResponse(HttpStatus.OK.value(), "Lấy thành công", pageResponse);
                }
                PageResponse pageResponse = new PageResponse<>(new PageImpl<>(Arrays.asList(menuDtoSet.toArray()), pageable, total));
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
            long total = influencerEntityRepository.countSubMenu(isFB, isYT, isIns, isTT, search.getIndustry(), search.getExpanse(), search.getFollower(), search.getProvinceId());
            List<InflucerDtoSubMenu> influcerDtoSubMenus = influencerEntityRepository.getSubMenu(isFB, isYT, isIns, isTT, search.getIndustry(), search.getExpanse(), search.getFollower(), search.getProvinceId(), pageable);
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
                        dtoDetails.setBankId(influencer.getBankId());
                        dtoDetails.setClassify(influencer.getClassify());
                        dtoDetails.setBankNumber(influencer.getBankNumber());
                        dtoDetails.setBirtYear(influencer.getBirtYear());
                        dtoDetails.setSex(influencer.getSex());
                        dtoDetails.setEmail(influencer.getEmail());
                        dtoDetails.setProvinceId(influencer.getProvinceId());
                        dtoDetails.setBirtYear(influencer.getBirtYear());
                        dtoDetails.setCreateHistory(DateFormatUtils.format(influencer.getCreateHistory(), "yyyy-MM-dd"));
                        dtoDetails.setPhone(influencer.getPhone());
                        dtoDetails.setIndustry(influencer.getIndustry());
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
            return new BaseResponse<>(HttpStatus.OK.value(), "Thêm thành công", dtoDetailsSet);
        } catch (Exception e) {
            return new ErrorResponse(Constant.FAILED, "Thêm thất bại", null);
        }
    }

    @Override
    @Transactional
    public Object add(InfluencerAddRequest request) {
        try {
            InfluencerEntity influencer = new InfluencerEntity();
            List<InfluencerDetailEntity> influencerDetailEntities = new ArrayList<>();
            influencer.setInflucerName(request.getName());
            influencer.setHistoryCreated(new Date());
            influencer.setSex(request.getSex());
            influencer.setPhone(request.getPhone());
            influencer.setYearOld(request.getBirtYear());
            influencer.setEmail(request.getEmail());
            influencer.setBankId(request.getBankId());
            influencer.setAccountNumber(request.getBankNumber());
            influencer.setIndustry(request.getIndustry());
            influencer.setAddress(request.getAddress());
            influencer.setProvinceId(request.getProvinceId());
            influencer.setClassifyId(request.getClassifyId());
            influencer.setFacebook(request.getIsFacebook());
            influencer.setTiktok(request.getIsTikTok());
            influencer.setYoutube(request.getIsYoutube());
            influencer.setInstagram(request.getIsInstagram());
            influencer = influencerEntityRepository.save(influencer);
            if (Boolean.TRUE.equals(request.getIsFacebook())) {
                InfluencerDetailEntity detailEntityFacebook = new InfluencerDetailEntity();
                detailEntityFacebook.setChannel("FACEBOOK".toUpperCase());
                detailEntityFacebook.setFollower(request.getFollowerFb());
                detailEntityFacebook.setExpense(request.getExpenseFb());
                detailEntityFacebook.setUrl(request.getLinkFb());
                detailEntityFacebook.setInfluId(influencer.getId());
                influencerDetailEntities.add(detailEntityFacebook);
            }
            if (Boolean.TRUE.equals(request.getIsTikTok())) {
                InfluencerDetailEntity detailEntityTikTok = new InfluencerDetailEntity();
                detailEntityTikTok.setChannel("TIKTOK".toUpperCase());
                detailEntityTikTok.setFollower(request.getFollowerTT());
                detailEntityTikTok.setExpense(request.getExpenseTT());
                detailEntityTikTok.setUrl(request.getLinkTT());
                detailEntityTikTok.setInfluId(influencer.getId());
                influencerDetailEntities.add(detailEntityTikTok);
            }
            if (Boolean.TRUE.equals(request.getIsYoutube())) {
                InfluencerDetailEntity detailEntityYoutube = new InfluencerDetailEntity();
                detailEntityYoutube.setChannel("YOUTUBE".toUpperCase());
                detailEntityYoutube.setFollower(request.getFollowerYT());
                detailEntityYoutube.setExpense(request.getExpenseYT());
                detailEntityYoutube.setUrl(String.valueOf(request.getLinkFb()));
                detailEntityYoutube.setInfluId(influencer.getId());
                influencerDetailEntities.add(detailEntityYoutube);
            }
            if (Boolean.TRUE.equals(request.getIsInstagram())) {
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
            Optional<InfluencerEntity> entity = influencerEntityRepository.findById(request.getId());
            List<InfluencerDetailEntity> influencerDetailEntities = new ArrayList<>();
            if (entity.isPresent()) {
                entity.get().setInflucerName(request.getName());
                entity.get().setHistoryCreated(new Date());
                entity.get().setSex(request.getSex());
                entity.get().setPhone(request.getPhone());
                entity.get().setYearOld(request.getBirtYear());
                entity.get().setEmail(request.getEmail());
                entity.get().setBankId(request.getBankId());
                entity.get().setAccountNumber(request.getBankNumber());
                entity.get().setIndustry(request.getIndustry());
                entity.get().setAddress(request.getAddress());
                entity.get().setProvinceId(request.getProvinceId());
                entity.get().setClassifyId(request.getClassifyId());
                entity.get().setFacebook(request.getIsFacebook());
                entity.get().setTiktok(request.getIsTikTok());
                entity.get().setYoutube(request.getIsYoutube());
                entity.get().setInstagram(request.getIsInstagram());
                influencerEntityRepository.save(entity.get());
                influencerDetailRepository.deleteByInfluId(entity.get().getId());
                if (Boolean.TRUE.equals(request.getIsFacebook())) {
                    InfluencerDetailEntity detailEntityFacebook = new InfluencerDetailEntity();
                    detailEntityFacebook.setChannel("FACEBOOK".toUpperCase());
                    detailEntityFacebook.setFollower(request.getFollowerFb());
                    detailEntityFacebook.setExpense(request.getExpenseFb());
                    detailEntityFacebook.setUrl(request.getLinkFb());
                    detailEntityFacebook.setInfluId(entity.get().getId());
                    influencerDetailEntities.add(detailEntityFacebook);
                }
                if (Boolean.TRUE.equals(request.getIsTikTok())) {
                    InfluencerDetailEntity detailEntityTikTok = new InfluencerDetailEntity();
                    detailEntityTikTok.setChannel("TIKTOK".toUpperCase());
                    detailEntityTikTok.setFollower(request.getFollowerTT());
                    detailEntityTikTok.setExpense(request.getExpenseTT());
                    detailEntityTikTok.setUrl(request.getLinkTT());
                    detailEntityTikTok.setInfluId(entity.get().getId());
                    influencerDetailEntities.add(detailEntityTikTok);
                }
                if (Boolean.TRUE.equals(request.getIsYoutube())) {
                    InfluencerDetailEntity detailEntityYoutube = new InfluencerDetailEntity();
                    detailEntityYoutube.setChannel("YOUTUBE".toUpperCase());
                    detailEntityYoutube.setFollower(request.getFollowerYT());
                    detailEntityYoutube.setExpense(request.getExpenseYT());
                    detailEntityYoutube.setUrl(String.valueOf(request.getLinkFb()));
                    detailEntityYoutube.setInfluId(entity.get().getId());
                    influencerDetailEntities.add(detailEntityYoutube);
                }
                if (Boolean.TRUE.equals(request.getIsInstagram())) {
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
            return new ErrorResponse(Constant.FAILED, "Sửa thất bại", null);
        } catch (Exception e) {
            return new ErrorResponse(Constant.FAILED, "Sửa thất bại", null);

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
}
