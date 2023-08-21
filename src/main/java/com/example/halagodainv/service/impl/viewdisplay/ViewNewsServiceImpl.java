package com.example.halagodainv.service.impl.viewdisplay;

import com.example.halagodainv.dto.topic.TopicDto;
import com.example.halagodainv.dto.viewnews.*;
import com.example.halagodainv.model.TopicEntity;
import com.example.halagodainv.model.viewdisplayentity.TagEntity;
import com.example.halagodainv.model.viewdisplayentity.ViewNewsEntity;
import com.example.halagodainv.repository.viewdisplay.TagRepository;
import com.example.halagodainv.repository.viewdisplay.TopicRepository;
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
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ViewNewsServiceImpl implements ViewNewsService {
    private final ViewNewsRepository viewNewsRepository;
    private final TopicRepository topicRepository;
    private final TagRepository tagRepository;

    public PageResponse<?> getViewNews(int pageNo, int pageSize, String language, Long topicId, Long tagId) {
        PageResponse<?> pageResponse;
        int offset = pageNo > 0 ? pageNo - 1 : 0;
        Pageable pageable = PageRequest.of(offset, pageSize, Sort.Direction.DESC, "id");
        List<ViewNewsMap> viewNewsMaps = viewNewsRepository.getAllTopic(topicId, tagId);
        List<ViewNewsDto> viewNewsDtos = new ArrayList<>();
        if (CollectionUtils.isEmpty(viewNewsMaps)) {
            pageResponse = new PageResponse<>(new PageImpl<>(viewNewsDtos, pageable, 0));
            return pageResponse;
        }
        for (ViewNewsMap map : viewNewsMaps) {
            ViewNewsDto viewNewsDto = new ViewNewsDto();
            viewNewsDto.setId(map.getId());
            viewNewsDto.setImage1(map.getImage1());
            viewNewsDto.setCreatedDate(map.getCreatedDate());
            if (language.equals("VN")) {
                viewNewsDto.setTitle(map.getTitle());
            } else if (language.equals("EN")) {
                viewNewsDto.setTitle(map.getTitleEN());
            }
            viewNewsDtos.add(viewNewsDto);
        }
        int pageEnd = Math.min((offset * pageSize) + pageSize, viewNewsDtos.size());
        pageResponse = new PageResponse<>(new PageImpl<>(viewNewsDtos.subList((offset * pageSize), pageEnd), pageable, viewNewsDtos.size()));
        return pageResponse;
    }

    public ViewNewsDetailDto getViewNewsDetail(int id, String language, Long topicId, Long tagId) {
        ViewNewsMap viewNewsMaps = viewNewsRepository.findByViewNewId(id, topicId, tagId);
        if (ObjectUtils.isEmpty(viewNewsMaps)) {
            return null;
        }
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

    public ViewNewsAndHotDetailDto getViewNewsAndHots(String language) {
        Pageable pageable = PageRequest.of(0, 3);
        List<ViewNewsMap> viewNewsMaps = viewNewsRepository.getNews(pageable);
        List<ViewNewsMap> viewNewHotsMap = viewNewsRepository.getNewHots(pageable);
        List<ViewNewsEntity> viewNewsEntities = viewNewsRepository.findAll();
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        int count5 = 0;
        ViewTopicDto viewTopicDto = new ViewTopicDto();
        ViewTopicDto viewTopicDto1 = new ViewTopicDto();
        ViewTopicDto viewTopicDto2 = new ViewTopicDto();
        ViewTopicDto viewTopicDto3 = new ViewTopicDto();
        ViewTopicDto viewTopicDto4 = new ViewTopicDto();
        List<ViewTopicDto> viewNewsTopicDto = new ArrayList<>();
        for (ViewNewsEntity viewMap : viewNewsEntities) {
            if (viewMap.getTopicId() == 1) {
                count1++;
            } else if (viewMap.getTopicId() == 2) {
                count2++;
            } else if (viewMap.getTopicId() == 3) {
                count3++;
            } else if (viewMap.getTopicId() == 4) {
                count4++;
            } else if (viewMap.getTopicId() == 5) {
                count5++;
            }
        }
        if (language.equals("VN")) {
            viewTopicDto.setTitle(topicRepository.findAll().get(0).getTopicName());
            viewTopicDto1.setTitle(topicRepository.findAll().get(1).getTopicName());
            viewTopicDto2.setTitle(topicRepository.findAll().get(2).getTopicName());
            viewTopicDto3.setTitle(topicRepository.findAll().get(3).getTopicName());
            viewTopicDto4.setTitle(topicRepository.findAll().get(4).getTopicName());
        } else {
            viewTopicDto.setTitle(topicRepository.findAll().get(0).getTopicNameEN());
            viewTopicDto1.setTitle(topicRepository.findAll().get(1).getTopicNameEN());
            viewTopicDto2.setTitle(topicRepository.findAll().get(2).getTopicNameEN());
            viewTopicDto3.setTitle(topicRepository.findAll().get(3).getTopicNameEN());
            viewTopicDto4.setTitle(topicRepository.findAll().get(4).getTopicNameEN());
        }
        viewTopicDto.setCount(count1);
        viewTopicDto1.setCount(count2);
        viewTopicDto2.setCount(count3);
        viewTopicDto3.setCount(count4);
        viewTopicDto4.setCount(count5);
        viewNewsTopicDto.add(viewTopicDto);
        viewNewsTopicDto.add(viewTopicDto1);
        viewNewsTopicDto.add(viewTopicDto2);
        viewNewsTopicDto.add(viewTopicDto3);
        viewNewsTopicDto.add(viewTopicDto4);

        List<ViewNewsHotDto> viewNews = new ArrayList<>();
        List<ViewNewsHotDto> viewNewHots = new ArrayList<>();
        viewNewsMaps.forEach(viewMap -> {
            ViewNewsHotDto viewNew = new ViewNewsHotDto();
            if (language.equals("VN")) {
                viewNew.setTitle(viewMap.getTitle());
                viewNew.setImage1(viewMap.getImage1());
            } else if (language.equals("EN")) {
                viewNew.setTitle(viewMap.getTitleEN());
                viewNew.setImage1(viewMap.getImage1());
            }
            viewNews.add(viewNew);
        });

        viewNewHotsMap.forEach(viewMap -> {
            ViewNewsHotDto viewNewsHotDto = new ViewNewsHotDto();
            if (language.equals("VN")) {
                viewNewsHotDto.setTitle(viewMap.getTitle());
                viewNewsHotDto.setImage1(viewMap.getImage1());
            } else if (language.equals("EN")) {
                viewNewsHotDto.setTitle(viewMap.getTitleEN());
                viewNewsHotDto.setImage1(viewMap.getImage1());
            }
            viewNewHots.add(new ViewNewsHotDto());
        });

        return new ViewNewsAndHotDetailDto(viewNewsTopicDto, viewNews, viewNewHots);
    }


    public Object updateNews(List<ViewNewsRequest> requests) {
        List<ViewNewsEntity> newsEntities = new ArrayList<>();
        for (ViewNewsRequest request : requests) {
            Optional<ViewNewsEntity> viewNews = viewNewsRepository.findById(request.getId());
            if (viewNews.isPresent()) {

                viewNews.get().setTitle(request.getTitle());
                viewNews.get().setBody(request.getBody());
                viewNews.get().setFooter(request.getFooter());
                viewNews.get().setHerder(request.getHerder());
                viewNews.get().setImage1(request.getImage1());
                viewNews.get().setImage2(request.getImage2());
                viewNews.get().setTopicId(request.getTopicId());
                viewNews.get().setIsHot(request.getIsHot());

                viewNews.get().setTitleEN(request.getTitleEN());
                viewNews.get().setHerderEN(request.getHerderEN());
                viewNews.get().setBodyEN(request.getBodyEN());
                viewNews.get().setFooterEN(request.getFooterEN());
                viewNews.get().setTagId(request.getTagId());
                newsEntities.add(viewNews.get());
            } else {
                ViewNewsEntity entity = new ViewNewsEntity();

                entity.setTitle(request.getTitle());
                entity.setBody(request.getBody());
                entity.setFooter(request.getFooter());
                entity.setHerder(request.getHerder());
                entity.setImage1(request.getImage1());
                entity.setImage2(request.getImage2());
                entity.setTopicId(request.getTopicId());
                entity.setIsHot(request.getIsHot());
                entity.setCreateDate(new Date());

                entity.setTitleEN(request.getTitleEN());
                entity.setHerderEN(request.getHerderEN());
                entity.setBodyEN(request.getBodyEN());
                entity.setFooterEN(request.getFooterEN());
                entity.setTagId(request.getTagId());
                newsEntities.add(entity);
            }
        }
        viewNewsRepository.saveAll(newsEntities);
        return newsEntities;
    }

    public List<TopicDto> getTopic(String language) {
        List<TopicEntity> entities = topicRepository.findAll();
        List<TopicDto> topicDtos = new ArrayList<>();
        entities.forEach(map -> {
            TopicDto topicDto = new TopicDto();
            if (language.equals("VN")) {
                topicDto.setId(map.getId());
                topicDto.setTopicName(map.getTopicName());
            } else if (language.equals("EN")) {
                topicDto.setId(map.getId());
                topicDto.setTopicName(map.getTopicNameEN());
            }
            topicDtos.add(topicDto);
        });
        return topicDtos;
    }

    public List<TagEntity> getTag() {
        return tagRepository.findAll();
    }
}
