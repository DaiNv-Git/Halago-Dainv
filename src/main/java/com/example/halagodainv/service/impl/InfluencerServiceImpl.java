package com.example.halagodainv.service.impl;


import com.example.halagodainv.config.Constant;
import com.example.halagodainv.model.Influencer;
import com.example.halagodainv.model.SocialNetworkInfluencer;
import com.example.halagodainv.repository.InfluencerRepository;
import com.example.halagodainv.repository.SocialNetworkInfluencerRepository;
import com.example.halagodainv.repository.jpaRepository.InfluencerNativeRepository;
import com.example.halagodainv.request.Influencer.InfluencerAddRequest;
import com.example.halagodainv.request.Influencer.InfluencerSearchRequest;
import com.example.halagodainv.request.Influencer.InfluencerSocialNetwordRequest;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.response.Influencer.InfluencerResponse;
import com.example.halagodainv.response.Influencer.InfluencerSearchDTO;
import com.example.halagodainv.response.PageResponse;
import com.example.halagodainv.service.InfluencerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
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
    private InfluencerNativeRepository influencerNativeRepository;
    @Autowired
    SocialNetworkInfluencerRepository socialNetworkInfluencerRepository;
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
        try{
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
            for(InfluencerSocialNetwordRequest social : request.getInfluencerRequestList()){
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
        }catch (Exception e){
            return new BaseResponse(Constant.FAILED, "Thêm influencer thất bại", new BaseResponse(0, e.getMessage(), null));
        }

    }

    @Override
    @Transactional
    @Modifying
    public Object delete(Integer id) {
        try{
            influencerRepository.deleteID(id);
            socialNetworkInfluencerRepository.deleteByInfluencerId(id);
            return new BaseResponse(Constant.SUCCESS, "Xóa  thành công", new BaseResponse(1, "Xóa  thành công", null));
        }catch (Exception e){
            return new BaseResponse(Constant.FAILED, "Xóa  thất bại", new BaseResponse(0, "Xóa  thất bại", null));

        }
    }

    @Override
    public PageResponse<InfluencerSearchDTO> searchInfluencers(InfluencerSearchRequest request) {
        Pageable pageable = PageRequest.of(request.getPageNo() - 1, request.getPageSize());
        Page<InfluencerSearchDTO> pageResult = influencerNativeRepository.searchInfluencers(
                request.getName(),
                request.getIndustryName(),
                request.isFb(),
                request.isYoutobe(),
                request.isTitok(),
                request.isInstagram(),
                pageable
        );
        return new PageResponse<>(pageResult);
    }
}
