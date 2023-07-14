package com.example.halagodainv.service.impl;

import com.example.halagodainv.common.Language;
import com.example.halagodainv.config.Constant;
import com.example.halagodainv.dto.news.NewDto;
import com.example.halagodainv.dto.news.NewDtoDetail;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
            int totalCountNews = (int) newsRepository.count();
            Pageable pageable = PageRequest.of(offset, newsSearch.getPageSize());
            List<NewDtoDetail> newsEntityList = newsRepository.getNewList(newsSearch.getTitle(), newsSearch.getStartDate() + " 00:00:00", newsSearch.getEndDate() + " 23:59:59", pageable);
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
        Optional<NewsEntity> newsEntity = newsRepository.findById(newId);
        if (newsEntity.isPresent()) {
            NewDto newDto = new NewDto(newsEntity.get(), newsEntity.get().getImageBrandMains());
            return new BaseResponse<>(HttpStatus.OK.value(), "lấy dữ liệu chi tiết thành công", newDto);
        }
        return new ErrorResponse(Constant.FAILED, "Lấy dữ liệu chi tiết thất bại", null);
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
            insertNews(newsAddRequest);
            return new BaseResponse(Constant.SUCCESS, "Sửa tin tức  thành công", new BaseResponse(1, "Sửa tin tức  thành công", null));
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
