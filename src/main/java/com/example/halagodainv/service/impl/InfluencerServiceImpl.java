package com.example.halagodainv.service.impl;


import com.example.halagodainv.config.Constant;
import com.example.halagodainv.dto.influcer.InflucerDtoListDetail;
import com.example.halagodainv.dto.influcer.InflucerMenuDto;
import com.example.halagodainv.dto.influcer.InflucerDtoSubMenu;
import com.example.halagodainv.dto.influcer.InfluencerDtoDetails;
import com.example.halagodainv.exception.ErrorResponse;
import com.example.halagodainv.model.Influencer;
import com.example.halagodainv.model.InfluencerDetailEntity;
import com.example.halagodainv.model.InfluencerEntity;
import com.example.halagodainv.repository.InfluencerDetailRepository;
import com.example.halagodainv.repository.InfluencerEntityRepository;
import com.example.halagodainv.repository.InfluencerRepository;
import com.example.halagodainv.repository.SocialNetworkInfluencerRepository;
import com.example.halagodainv.request.influencer.InFluencerSubMenuSearch;
import com.example.halagodainv.request.influencer.InfluencerAddRequest;
import com.example.halagodainv.request.influencer.InfluencerDetailNetworkRequest;
import com.example.halagodainv.request.influencer.InfluencerSearch;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.response.Influencer.InfluencerResponse;
import com.example.halagodainv.response.PageResponse;
import com.example.halagodainv.service.InfluencerService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import utils.StringUtils;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class InfluencerServiceImpl implements InfluencerService {
    private final InfluencerRepository influencerRepository;
    private final SocialNetworkInfluencerRepository socialNetworkInfluencerRepository;
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
            long total = influencerEntityRepository.totalCount(isFB, isYT, isIns, isTT, search.getIndustry(), search.getProvinceId());
            List<InflucerMenuDto> influcerMenuDtos = influencerEntityRepository.getAll(isFB, isYT, isIns, isTT, search.getIndustry(), search.getProvinceId(), pageable);
            if (CollectionUtils.isEmpty(influcerMenuDtos)) {
                PageResponse pageResponse = new PageResponse<>(new PageImpl<>(influcerMenuDtos, pageable, 0));
                return new BaseResponse(HttpStatus.OK.value(), "Lấy thành công", pageResponse);
            }
            PageResponse pageResponse = new PageResponse<>(new PageImpl<>(influcerMenuDtos, pageable, total));
            return new BaseResponse(HttpStatus.OK.value(), "Lấy thành công", pageResponse);
        } catch (Exception e) {
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Lấy thất bai", null);
        }

    }

    public Object getInfluSubMenu(InFluencerSubMenuSearch search) {
        try {
            int offset = 0;
            if (search.getPageNo() > 0) offset = search.getPageNo() - 1;
            Boolean isFB = search.getIsFacebook() != null ? search.getIsFacebook() : null;
            Boolean isIns = search.getIsInstagram() != null ? search.getIsInstagram() : null;
            Boolean isTT = search.getIsTikTok() != null ? search.getIsTikTok() : null;
            Boolean isYT = search.getIsYoutube() != null ? search.getIsYoutube() : null;
            Pageable pageable = PageRequest.of(offset, search.getPageSize(), Sort.Direction.DESC, "id");
            long total = influencerEntityRepository.countSubMenu(isFB, isYT, isIns, isTT, search.getExpanse(), search.getFollower(), search.getIndustry());
            List<InflucerDtoSubMenu> influcerDtoSubMenus = influencerEntityRepository.getSubMenu(isFB, isYT, isIns, isTT, search.getExpanse(), search.getFollower(), search.getIndustry(), pageable);
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
                        dtoDetails.setIsSex(influencer.isSex());
                        dtoDetails.setProvinceId(influencer.getProvinceId());
                        dtoDetails.setBirtYear(influencer.getBirtYear());
                        dtoDetails.setCreateHistory(DateFormatUtils.format(influencer.getCreateHistory(), "yyyy-MM-dd"));
                        dtoDetails.setPhone(influencer.getPhone());
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
            influencer.setSex(request.isSex());
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
                detailEntityFacebook.setFollower(String.valueOf(request.getFollowerFb()) + "K");
                detailEntityFacebook.setExpense(String.valueOf(request.getExpenseFb()) + "K");
                detailEntityFacebook.setUrl(String.valueOf(request.getLinkFb()));
                detailEntityFacebook.setInfluId(influencer.getId());
                influencerDetailEntities.add(detailEntityFacebook);
            }
            if (Boolean.TRUE.equals(request.getIsTikTok())) {
                InfluencerDetailEntity detailEntityTikTok = new InfluencerDetailEntity();
                detailEntityTikTok.setChannel("TIKTOK".toUpperCase());
                detailEntityTikTok.setFollower(String.valueOf(request.getFollowerTT()) + "K");
                detailEntityTikTok.setExpense(String.valueOf(request.getExpenseTT()) + "K");
                detailEntityTikTok.setUrl(String.valueOf(request.getLinkTT()));
                detailEntityTikTok.setInfluId(influencer.getId());
                influencerDetailEntities.add(detailEntityTikTok);
            }
            if (Boolean.TRUE.equals(request.getIsYoutube())) {
                InfluencerDetailEntity detailEntityYoutube = new InfluencerDetailEntity();
                detailEntityYoutube.setChannel("YOUTUBE".toUpperCase());
                detailEntityYoutube.setFollower(String.valueOf(request.getFollowerYT()) + "K");
                detailEntityYoutube.setExpense(String.valueOf(request.getExpenseYT()) + "K");
                detailEntityYoutube.setUrl(String.valueOf(request.getLinkFb()));
                detailEntityYoutube.setInfluId(influencer.getId());
                influencerDetailEntities.add(detailEntityYoutube);
            }
            if (Boolean.TRUE.equals(request.getIsInstagram())) {
                InfluencerDetailEntity detailEntityInstagram = new InfluencerDetailEntity();
                detailEntityInstagram.setChannel("INSTAGRAM".toUpperCase());
                detailEntityInstagram.setFollower(String.valueOf(request.getFollowerIns()) + "K");
                detailEntityInstagram.setExpense(String.valueOf(request.getExpenseIns()) + "K");
                detailEntityInstagram.setUrl(String.valueOf(request.getLinkIns()));
                detailEntityInstagram.setInfluId(influencer.getId());
                influencerDetailEntities.add(detailEntityInstagram);
            }
            influencerDetailRepository.saveAll(influencerDetailEntities);
            return new BaseResponse<>(HttpStatus.OK.value(), "Thêm thành công", influencerEntityRepository.getDetails(influencer.getId()));
        } catch (Exception e) {
            return new ErrorResponse(Constant.FAILED, "Thêm thất bại", null);

        }
    }

    @Override
    @Transactional
    @Modifying
    public Object delete(Integer id) {
        try {
            influencerRepository.deleteID(id);
            socialNetworkInfluencerRepository.deleteByInfluencerId(id);
            return new BaseResponse(Constant.SUCCESS, "Xóa  thành công", new BaseResponse(1, "Xóa  thành công", null));
        } catch (Exception e) {
            return new BaseResponse(Constant.FAILED, "Xóa  thất bại", new BaseResponse(0, "Xóa  thất bại", null));

        }
    }
}
