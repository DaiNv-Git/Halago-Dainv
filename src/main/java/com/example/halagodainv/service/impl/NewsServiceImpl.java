package com.example.halagodainv.service.impl;
import com.example.halagodainv.common.Language;
import com.example.halagodainv.config.Constant;
import com.example.halagodainv.model.News;
import com.example.halagodainv.model.NewsLanguage;
import com.example.halagodainv.model.NewsType;
import com.example.halagodainv.repository.NewsLanguageRepository;
import com.example.halagodainv.repository.NewsRepository;
import com.example.halagodainv.repository.NewsTypeRepository;
import com.example.halagodainv.request.news.NewsAddRequest;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.service.NewsService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    NewsLanguageRepository newsLanguageRepository;

    @Autowired
    NewsTypeRepository newsTypeRepository;

    @Autowired
    NewsRepository newsRepository;
    BaseResponse responseData = new BaseResponse(1, "Chưa có dữ liệu", null);

    @Override
    public Object insertNews(NewsAddRequest request) {
        try{
            //add news
            News news = new News();
            NewsLanguage newsEN = new NewsLanguage();
            NewsLanguage newsVN = new NewsLanguage();
            news.setThumbnail(request.getThumbnail());
            news.setCreated(new Date());
            news.setType(request.getType());
            news.setStatus(request.getStatus());
            newsRepository.save(news);
            //add news language
            //add en
            newsEN.setTitle(request.getTitleEN());
            newsEN.setContent(request.getContentEN());
            newsEN.setDescription(request.getDescriptionEN());
            newsEN.setLanguage(String.valueOf(Language.EN));
            newsEN.setIdNews(news.getIdNews());
            newsLanguageRepository.save(newsEN);
            //add vn
            newsVN.setTitle(request.getTitleVN());
            newsVN.setContent(request.getContentVN());
            newsVN.setDescription(request.getDescriptionVN());
            newsVN.setLanguage(String.valueOf(Language.VN));
            newsVN.setIdNews(news.getIdNews());
            newsLanguageRepository.save(newsVN);
            return new BaseResponse(Constant.SUCCESS, "Thêm tin tức  thành công", new BaseResponse(1, "Thêm tin tức  thành công",news));
        }catch (Exception e){
            return  new BaseResponse(Constant.FAILED, "Thêm tin tức  thất bại", new BaseResponse(0, "Thêm tin tức  thất bại",null));
        }
    }

    @Override
    @Transactional
    @Modifying
    public Object update(NewsAddRequest newsAddRequest) {
        try{
            newsLanguageRepository.deleteByIdNative(newsAddRequest.getIdNews());
            newsRepository.deleteById(newsAddRequest.getIdNews());
            insertNews(newsAddRequest);
            return new BaseResponse(Constant.SUCCESS, "Sửa tin tức  thành công", new BaseResponse(1, "Sửa tin tức  thành công",null));
        }catch (Exception e){
            return  new BaseResponse(Constant.FAILED, "Sửa tin tức  thất bại", new BaseResponse(0, "Sửa tin tức  thất bại",null));
        }
    }

    @Override
    @Transactional
    @Modifying
    public Object delete(Integer id) {
        try{
            newsLanguageRepository.deleteByIdNative(id);
            newsRepository.deleteById(id);
            return new BaseResponse(Constant.SUCCESS, "Xóa tin tức  thành công", new BaseResponse(1, "Xóa tin tức  thành công",null));
        }catch (Exception e){
            return new BaseResponse(Constant.FAILED, "Xóa tin tức  thất bại", new BaseResponse(0, "Xóa tin tức  thất bại\"",null));
        }

    }
}
