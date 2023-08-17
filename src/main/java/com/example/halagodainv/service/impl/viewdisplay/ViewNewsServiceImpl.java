package com.example.halagodainv.service.impl.viewdisplay;

import com.example.halagodainv.dto.viewnews.ViewNewsDetailDto;
import com.example.halagodainv.dto.viewnews.ViewNewsDto;
import com.example.halagodainv.dto.viewnews.ViewNewsMap;
import com.example.halagodainv.model.viewdisplayentity.ViewNewsEntity;
import com.example.halagodainv.model.viewdisplayentity.ViewNewsLanguageEntity;
import com.example.halagodainv.repository.viewdisplay.ViewNewsLanguageRepository;
import com.example.halagodainv.repository.viewdisplay.ViewNewsRepository;
import com.example.halagodainv.request.news.ViewNewsRequest;
import com.example.halagodainv.response.PageResponse;
import com.example.halagodainv.service.ViewNewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ViewNewsServiceImpl implements ViewNewsService {
    private final ViewNewsRepository viewNewsRepository;
    private final ViewNewsLanguageRepository viewNewsLanguageRepository;

    public PageResponse<?> getViewNews(int pageNo, int pageSize, String language, Long topicId) {
        PageResponse<?> pageResponse;
        int offset = pageNo > 0 ? pageNo - 1 : 0;
        Pageable pageable = PageRequest.of(offset, pageSize, Sort.Direction.DESC, "id");
        List<ViewNewsMap> viewNewsMaps = viewNewsRepository.getAllTopic(topicId);
        List<ViewNewsDto> viewNewsDtos = new ArrayList<>();

        if (CollectionUtils.isEmpty(viewNewsMaps)) {
            pageResponse = new PageResponse<>(new PageImpl<>(viewNewsDtos, pageable, 0));
            return pageResponse;
        }
        for (ViewNewsMap map : viewNewsMaps) {
            ViewNewsDto viewNewsDto = new ViewNewsDto();
            viewNewsDto.setId(map.getId());
            viewNewsDto.setImage1(map.getImage1());
            viewNewsDto.setImage2(map.getImage2());
            viewNewsDto.setCreatedDate(map.getCreatedDate());
            if (language.equals("VN")) {
                viewNewsDto.setTitle(map.getTitle());
                viewNewsDto.setHerder(map.getHerder());
            } else if (language.equals("EN")) {
                viewNewsDto.setTitle(map.getTitleEN());
                viewNewsDto.setHerder(map.getHerderEN());
            }
            viewNewsDtos.add(viewNewsDto);
        }
        int pageEnd = Math.min((offset * pageSize) + pageSize, viewNewsDtos.size());
        pageResponse = new PageResponse<>(new PageImpl<>(viewNewsDtos.subList((offset * pageSize), pageEnd), pageable, viewNewsDtos.size()));
        return pageResponse;
    }

    public ViewNewsDetailDto getViewNewsDetail(int id, String language, Long topicId) {
        ViewNewsMap viewNewsMaps = viewNewsRepository.findByViewNewId(id, topicId);
        ViewNewsDetailDto viewNewsDetailDto = new ViewNewsDetailDto();
        viewNewsDetailDto.setId(viewNewsMaps.getId());
        viewNewsDetailDto.setImage1(viewNewsMaps.getImage1());
        viewNewsDetailDto.setImage2(viewNewsMaps.getImage2());
        viewNewsDetailDto.setCreatedDate(viewNewsMaps.getCreatedDate());
        if (language.equals("VN")) {
            viewNewsDetailDto.setTitle(viewNewsMaps.getTitle());
            viewNewsDetailDto.setHerder(viewNewsMaps.getHerder());
            viewNewsDetailDto.setBody(viewNewsMaps.getBody());
            viewNewsDetailDto.setFooter(viewNewsMaps.getFooter());
        } else if (language.equals("EN")) {
            viewNewsDetailDto.setTitle(viewNewsMaps.getTitleEN());
            viewNewsDetailDto.setHerder(viewNewsMaps.getHerderEN());
            viewNewsDetailDto.setBody(viewNewsMaps.getBody());
            viewNewsDetailDto.setFooter(viewNewsMaps.getFooter());
        }
        return viewNewsDetailDto;
    }

    public PageResponse<?> getDetail(int pageNo, int pageSize) {
        PageResponse<?> pageResponse;
        int offset = pageNo > 0 ? pageNo - 1 : 0;
        Pageable pageable = PageRequest.of(offset, pageSize, Sort.Direction.DESC, "id");
        List<ViewNewsMap> viewNewsMaps = viewNewsRepository.getALl(pageable);
        if (CollectionUtils.isEmpty(viewNewsMaps)) {
            pageResponse = new PageResponse<>(new PageImpl<>(viewNewsMaps, pageable, 0));
            return pageResponse;
        }
        pageResponse = new PageResponse<>(new PageImpl<>(viewNewsMaps, pageable, viewNewsMaps.size()));
        return pageResponse;
    }


    public Object updateNews(List<ViewNewsRequest> requests) {
        for (ViewNewsRequest request : requests) {
            Optional<ViewNewsEntity> viewNews = viewNewsRepository.findById(request.getId());
            List<ViewNewsEntity> newsEntities = new ArrayList<>();
            List<ViewNewsLanguageEntity> languageEntities = new ArrayList<>();
            if (viewNews.isPresent()) {
                Optional<ViewNewsLanguageEntity> viewNewsLanguageEntity = viewNewsLanguageRepository.findById(viewNews.get().getId());
                viewNews.get().setBody(request.getBody());
                viewNews.get().setFooter(request.getFooter());
                viewNews.get().setHerder(request.getHerder());
                viewNews.get().setImage1(request.getImage1());
                viewNews.get().setImage2(request.getImage2());
                viewNews.get().setTopicId(request.getTopicId());
                viewNewsLanguageEntity.get().setHerderEN(request.getHerderEN());
                viewNewsLanguageEntity.get().setBodyEN(request.getBodyEN());
                viewNewsLanguageEntity.get().setFooterEN(request.getFooterEN());
                newsEntities.add(viewNews.get());
                languageEntities.add(viewNewsLanguageEntity.get());

            } else {
                ViewNewsEntity entity = new ViewNewsEntity();
                entity.setBody(request.getBody());
                entity.setFooter(request.getFooter());
                entity.setHerder(request.getHerder());
                entity.setImage1(request.getImage1());
                entity.setImage2(request.getImage2());
                entity.setTopicId(request.getTopicId());
                entity.setCreateDate(new Date());
                entity = viewNewsRepository.save(entity);
                ViewNewsLanguageEntity viewNewsLanguage = new ViewNewsLanguageEntity();
                viewNewsLanguage.setHerderEN(request.getHerderEN());
                viewNewsLanguage.setBodyEN(request.getBodyEN());
                viewNewsLanguage.setFooterEN(request.getFooterEN());
                viewNewsLanguage.setNewViewId(entity.getId());
                viewNewsLanguageRepository.save(viewNewsLanguage);
            }
            viewNewsRepository.saveAll(newsEntities);
            viewNewsLanguageRepository.saveAll(languageEntities);

        }
        return getDetail(1, 10);
    }
}
