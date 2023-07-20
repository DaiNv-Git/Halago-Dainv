package com.example.halagodainv.service.impl;


import com.example.halagodainv.config.Constant;
import com.example.halagodainv.dto.influcer.InflucerDto;
import com.example.halagodainv.model.Influencer;
import com.example.halagodainv.model.SocialNetworkInfluencer;
import com.example.halagodainv.repository.InfluencerEntityRepository;
import com.example.halagodainv.repository.InfluencerRepository;
import com.example.halagodainv.repository.SocialNetworkInfluencerRepository;
import com.example.halagodainv.request.influencer.InfluencerAddRequest;
import com.example.halagodainv.request.Influencer.InfluencerSearch;
import com.example.halagodainv.request.influencer.InfluencerSocialNetwordRequest;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.response.Influencer.InfluencerResponse;
import com.example.halagodainv.response.PageResponse;
import com.example.halagodainv.service.InfluencerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class InfluencerServiceImpl implements InfluencerService {
    @Autowired
    InfluencerRepository influencerRepository;
    @Autowired
    SocialNetworkInfluencerRepository socialNetworkInfluencerRepository;

    @Autowired
    private InfluencerEntityRepository influencerEntityRepository;

    @Override
    public Object getInfluMenu(InfluencerSearch search) {
        int offset = 0;
        if (search.getPageNo() > 0) offset = search.getPageNo() - 1;
        Boolean isFB = search.getIsFacebook() != null ? search.getIsFacebook() : null;
        Boolean isIns = search.getIsInstagram() != null ? search.getIsInstagram() : null;
        Boolean isTT = search.getIsTikTok() != null ? search.getIsTikTok() : null;
        Boolean isYT = search.getIsYoutube() != null ? search.getIsYoutube() : null;
        Pageable pageable = PageRequest.of(offset, search.getPageSize(), Sort.Direction.DESC, "id");
        long total = influencerEntityRepository.totalCount(isFB, isYT, isIns, isTT, search.getIndustry(), search.getProvinceId());
        List<InflucerDto> influcerDtos = influencerEntityRepository.getAll(isFB, isYT, isIns, isTT, search.getIndustry(), search.getProvinceId(), pageable);
        if (CollectionUtils.isEmpty(influcerDtos)) {
            PageResponse pageResponse = new PageResponse<>(new PageImpl<>(influcerDtos, pageable, 0));
            return new BaseResponse(HttpStatus.OK.value(), "success", pageResponse);
        }
        PageResponse pageResponse = new PageResponse<>(new PageImpl<>(influcerDtos, pageable, total));
        return new BaseResponse(HttpStatus.OK.value(), "success", pageResponse);
    }

    @Override
    public InfluencerResponse findInfluencerById(int id) {
        Influencer influencer = influencerRepository.findInfluencer(id);
        InfluencerResponse influencerDao = new InfluencerResponse();
        BeanUtils.copyProperties(influencer, influencerDao);
        influencerDao.setIndustryId(StringUtils.hashTagToIntArray(influencer.getIndustryId()));
        influencerDao.setChannelInteraction(StringUtils.hashTagToIntArray(influencer.getChannelInteraction()));
        influencerDao.setTypesInteraction(StringUtils.hashTagToIntArray(influencer.getTypesInteraction()));
        if (influencer.getStatus() == 1) {
            influencerDao.setStatusName("Active");
        } else {
            influencerDao.setStatusName("InActive");
        }
        return influencerDao;
    }

    @Override
    @Transactional
    public Object add(InfluencerAddRequest request) {
        try {
            Influencer influencer = new Influencer();
            influencer.setName(request.getName());
            influencer.setPhone(request.getPhoneNumber());
            influencer.setSex(request.getSex());
            influencer.setBirthday(request.getYear());
            influencer.setYoutobe(request.getYoutobe());
            influencer.setTiktok(request.getTiktok());
            influencer.setFacebook(request.getFacebook());
            influencer.setInstagram(request.getInstagram());
            influencer.setEmail(request.getEmail());
            influencer.setCity(request.getProvince());
            influencer.setClassify(request.getClassify());
            //LINH Vuc
            influencer.setIndustryId(request.getField());
            influencer.setAddress(request.getAddress());
            influencer.setBankName(request.getBankName());
            influencer.setBankAccount(request.getBankNumber());
            influencer.setCreated(new Date());
            influencerRepository.save(influencer);
            List<SocialNetworkInfluencer> addSocialList = new ArrayList<>();
            for (InfluencerSocialNetwordRequest social : request.getInfluencerRequestList()) {
                SocialNetworkInfluencer socialNetworkInfluencer = new SocialNetworkInfluencer();
                socialNetworkInfluencer.setInfluencerID(influencer.getId());
                socialNetworkInfluencer.setLink(social.getLink());
                socialNetworkInfluencer.setChannelID(social.getChannelId());
                socialNetworkInfluencer.setExpense(social.getExpense());
                socialNetworkInfluencer.setFollower(social.getFollower());
                addSocialList.add(socialNetworkInfluencer);
            }

            socialNetworkInfluencerRepository.saveAll(addSocialList);
            return new BaseResponse(Constant.SUCCESS, "Thêm influencer tức  thành công", new BaseResponse(1, "Thêm influencer tức  thành công", influencer));
        } catch (Exception e) {
            return new BaseResponse(Constant.FAILED, "Thêm influencer thất bại", new BaseResponse(0, e.getMessage(), null));
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
