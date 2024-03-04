package com.example.halagodainv.service.impl;

import com.example.halagodainv.dto.hompage.*;;
import com.example.halagodainv.exception.ErrorResponse;
import com.example.halagodainv.exception.GeneralException;
import com.example.halagodainv.model.viewdisplayentity.HomepageEntitty;
import com.example.halagodainv.model.viewdisplayentity.PartnerEntity;
import com.example.halagodainv.repository.viewdisplay.HomePageRepository;
import com.example.halagodainv.repository.viewdisplay.PartnerRepository;
import com.example.halagodainv.request.homepage.HomeUpdateRequest;
import com.example.halagodainv.request.homepage.PartnerRequest;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.service.HomePageService;
import com.example.halagodainv.until.FileImageUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Service
@RequiredArgsConstructor
public class HomePageServiceImpl implements HomePageService {
    private final HomePageRepository homePageRepository;
    private final PartnerRepository partnerRepository;
    private final FileImageUtil fileImageUtil;
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Object getHomePage(String language) throws GeneralException {
        try {
            List<HomePageDetail> homePageDetails = new ArrayList<>();
            homePageDetails.add(new HomePageDetail(homePageRepository.findAll(), language));
            HomePageDto homePageDto = new HomePageDto(homePageDetails, getNewLimit10(language).size() > 0 ? getNewLimit10(language) : new ArrayList<>());
            return new BaseResponse<>(HttpStatus.OK.value(), "success", homePageDto);
        } catch (Exception e) {
            return new ErrorResponse<>(500, e.getLocalizedMessage(), null);
        }
    }

    private List<NewsTenDto> getNewLimit10(String language){
        String sql = "select n.link_papers as linkPagers,n.title_seo as titleImage,nl.title, n.thumbnail as " +
                "image,nl.description,DATE_FORMAT(n.created,'%Y-%m-%d') as created,n.author_avatar as avatar,n.author_name as " +
                "nameAuthor from news n left join news_language nl on n.id_news = nl.new_id " +
                "where n.topic_id = 1 and n.news_from_kol <> 1 and nl.language= '"+language+"' order by n.created desc limit 10 ";
        Query nativeQuery = entityManager.createNativeQuery(sql);
        return nativeQuery.unwrap(NativeQuery.class).setResultTransformer(Transformers.aliasToBean(NewsTenDto.class)).getResultList();
    }

    public Object getDetail() throws GeneralException {
        return homePageRepository.findAll();
    }

    public Object updateHomePage(HomeUpdateRequest request) throws GeneralException {
        try {
            homePageRepository.deleteAll();
            HomepageEntitty homepageEntitty = new HomepageEntitty();
            homepageEntitty.setForeignBrands(request.getForeignBrands());
            homepageEntitty.setDomesticBrands(request.getDomesticBrands());
            homepageEntitty.setSuccessfulCampaign(request.getSuccessfulCampaign());
            homepageEntitty.setKOLsInformational(request.getKOLsInformational());
            homepageEntitty.setTitleVN(request.getTitleVN());
            homepageEntitty.setTitleEN(request.getTitleEN());
            homepageEntitty = homePageRepository.save(homepageEntitty);
            return new BaseResponse<>(HttpStatus.OK.value(), "edit success", homepageEntitty);
        } catch (Exception e) {
            throw new GeneralException(e.getLocalizedMessage());
        }
    }

    public Object getPartner(int partnerId) throws GeneralException {
        try {
            Query query = entityManager.createNativeQuery("SELECT * FROM partner WHERE partner_id ="+partnerId+" ORDER BY IFNULL(SUBSTRING_INDEX(name_file, '_', -1),'') DESC ", PartnerEntity.class);
            List<PartnerEntity> partnerEntities = query.getResultList();
            return new BaseResponse<>(HttpStatus.OK.value(), "success", partnerEntities);
        } catch (Exception e) {
            throw new GeneralException(e.getLocalizedMessage());
        }
    }

    public Object updateLogo(List<PartnerRequest> partnerRequests) {
        List<PartnerEntity> partnerEntities = new ArrayList<>();
        partnerRepository.deleteByPartnerId(partnerRequests.get(0).getPartnerId());
        for (PartnerRequest partnerRequest : partnerRequests) {
            PartnerEntity partnerNew = new PartnerEntity();
            partnerNew.setLogo(fileImageUtil.uploadImage(partnerRequest.getLogo()));
            partnerNew.setIndexLogo(partnerRequest.getIndexLogo());
            partnerNew.setNameFile(partnerRequest.getNameFile());
            partnerNew.setPartnerId(partnerRequests.get(0).getPartnerId());
            partnerEntities.add(partnerNew);
        }
        partnerRepository.saveAll(partnerEntities);
        return new BaseResponse<>(HttpStatus.OK.value(), "success", partnerEntities);
    }

    public void deleteLogo(Long id) {
        partnerRepository.deleteById(id);
    }
}
