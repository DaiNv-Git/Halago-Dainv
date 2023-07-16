package com.example.halagodainv.service.impl;

import com.example.halagodainv.common.Language;
import com.example.halagodainv.config.Constant;
import com.example.halagodainv.dto.news.NewDetails;
import com.example.halagodainv.dto.news.NewDto;
import com.example.halagodainv.dto.news.NewDtoDetails;
import com.example.halagodainv.exception.ErrorResponse;
import com.example.halagodainv.model.NewsEntity;
import com.example.halagodainv.model.NewsLanguageEntity;
import com.example.halagodainv.repository.NewsLanguageRepository;
import com.example.halagodainv.repository.NewsRepository;
import com.example.halagodainv.repository.NewsTypeRepository;
import com.example.halagodainv.request.news.NewsAddRequest;
import com.example.halagodainv.request.news.NewsSearch;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.response.PageResponse;
import com.example.halagodainv.service.NewsService;
import com.example.halagodainv.until.FormatTimeSearch;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    NewsLanguageRepository newsLanguageRepository;

    @Autowired
    NewsTypeRepository newsTypeRepository;

    @Autowired
    NewsRepository newsRepository;

    @Override
    public Object getNews(NewsSearch newsSearch) {
        try {
            int offset = 0;
            if (newsSearch.getPageNo() > 0) {
                offset = newsSearch.getPageNo() - 1;
            }
            int totalCountNews = newsRepository.countByAll(newsSearch.getTitle(), FormatTimeSearch.getStart(newsSearch.getStartDate()), FormatTimeSearch.getEndDate(newsSearch.getEndDate()));
            Pageable pageable = PageRequest.of(offset, newsSearch.getPageSize());
            List<NewDto> newsEntityList = newsRepository.getNewList(newsSearch.getTitle(), FormatTimeSearch.getStart(newsSearch.getStartDate()), FormatTimeSearch.getEndDate(newsSearch.getEndDate()), pageable);
            PageResponse pageResponse;
            if (CollectionUtils.isEmpty(newsEntityList)) {
                pageResponse = new PageResponse(new PageImpl(newsEntityList, pageable, 0));
                return new BaseResponse<>(200, "Lấy dữ liệu thành công", pageResponse);
            }
            pageResponse = new PageResponse(new PageImpl(newsEntityList, pageable, totalCountNews));
            return new BaseResponse<>(200, "Lấy dữ liệu thành công", pageResponse);
        } catch (Exception e) {
            return new ErrorResponse(500, "Lấy dữ liệu thất bại", null);
        }
    }

    @Override
    public Object getDetail(int newId) {
        try {
            List<NewDetails> newsRepositoryDetail = newsRepository.getDetail(newId);
            Set<NewDtoDetails> newDtoDetail = new HashSet<>();
            NewDtoDetails newewDtoDetailsss = new NewDtoDetails();
            newsRepositoryDetail.forEach(
                    i -> {
                        newewDtoDetailsss.setIdNews(i.getIdNews());
                        newewDtoDetailsss.setThumbnail(i.getThumbnail());
                        newewDtoDetailsss.setType(i.getType());
                        newewDtoDetailsss.setStatus(i.getStatus());
                        newewDtoDetailsss.setLinkPost(i.getLinkPost());
                        newewDtoDetailsss.setPhotoTitle(i.getPhotoTitle());
                        if (i.getLanguage().equals("VN")) {
                            newewDtoDetailsss.setContentVN(i.getContent());
                            newewDtoDetailsss.setDescriptionVN(i.getDescription());
                            newewDtoDetailsss.setTitleVN(i.getTitle());
                        } else {
                            newewDtoDetailsss.setTitleEN(i.getTitle());
                            newewDtoDetailsss.setDescriptionEN(i.getDescription());
                            newewDtoDetailsss.setContentEN(i.getContent());
                        }
                    });
            newDtoDetail.add(newewDtoDetailsss);
            return new BaseResponse<>(HttpStatus.OK.value(), "lấy dữ liệu chi tiết thành công", newDtoDetail);
        } catch (Exception e) {
            return new ErrorResponse(Constant.FAILED, "Lấy dữ liệu chi tiết thất bại", null);
        }
    }

    @Override
    public Object insertNews(NewsAddRequest request) {
        try {
            //add news
            NewsEntity newsEntity = new NewsEntity();
            NewsLanguageEntity newsEN = new NewsLanguageEntity();
            NewsLanguageEntity newsVN = new NewsLanguageEntity();
            newsEntity.setThumbnail(request.getThumbnail());
            newsEntity.setCreated(new Date());
            newsEntity.setTitleSeo(request.getPhotoTitle());
            newsEntity.setLinkPapers(request.getLinkPost());
            newsEntity.setType(request.getType());
            newsEntity.setStatus(request.getStatus());
            newsRepository.save(newsEntity);
            //add news language
            //add en
            newsEN.setTitle(request.getTitleEN());
            newsEN.setContent(request.getContentEN());
            newsEN.setDescription(request.getDescriptionEN());
            newsEN.setLanguage(String.valueOf(Language.EN));
            newsEN.setNewsEntity(newsEntity);
            newsLanguageRepository.save(newsEN);
            //add vn
            newsVN.setTitle(request.getTitleVN());
            newsVN.setContent(request.getContentVN());
            newsVN.setDescription(request.getDescriptionVN());
            newsVN.setLanguage(String.valueOf(Language.VN));
            newsVN.setNewsEntity(newsEntity);
            newsLanguageRepository.save(newsVN);
            return new BaseResponse(Constant.SUCCESS, "Thêm tin tức  thành công", new BaseResponse(1, "Thêm tin tức  thành công", newsEntity));
        } catch (Exception e) {
            return new BaseResponse(Constant.FAILED, "Thêm tin tức  thất bại", new BaseResponse(0, "Thêm tin tức  thất bại", null));
        }
    }

    @Override
    @Transactional
    @Modifying
    public Object update(NewsAddRequest newsAddRequest) {
        try {
            newsLanguageRepository.deleteByNewsEntity_IdNews(newsAddRequest.getIdNews());
            newsRepository.deleteById(newsAddRequest.getIdNews());
            Object res = insertNews(newsAddRequest);
            return new BaseResponse(Constant.SUCCESS, "Sửa tin tức  thành công", new BaseResponse(1, "Sửa tin tức  thành công", res));
        } catch (Exception e) {
            return new BaseResponse(Constant.FAILED, "Sửa tin tức  thất bại", new BaseResponse(0, "Sửa tin tức  thất bại", null));
        }
    }

    @Override
    @Transactional
    @Modifying
    public Object delete(Integer id) {
        try {
            newsLanguageRepository.deleteByNewsEntity_IdNews(id);
            newsRepository.deleteById(id);
            return new BaseResponse(Constant.SUCCESS, "Xóa tin tức  thành công", new BaseResponse(1, "Xóa tin tức  thành công", null));
        } catch (Exception e) {
            return new BaseResponse(Constant.FAILED, "Xóa tin tức  thất bại", new BaseResponse(0, "Xóa tin tức  thất bại\"", null));
        }

    }
}
