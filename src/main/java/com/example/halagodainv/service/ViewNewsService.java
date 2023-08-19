package com.example.halagodainv.service;

import com.example.halagodainv.dto.topic.TopicDto;
import com.example.halagodainv.dto.viewnews.ViewNewsAndHotDetailDto;
import com.example.halagodainv.dto.viewnews.ViewNewsDetailDto;
import com.example.halagodainv.model.viewdisplayentity.TagEntity;
import com.example.halagodainv.request.news.ViewNewsRequest;
import com.example.halagodainv.response.PageResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public interface ViewNewsService {
    PageResponse<?> getViewNews(int pageNo, int pageSize, String language, Long topicId, Long tagId);

    ViewNewsDetailDto getViewNewsDetail(int id, String language, Long topicId, Long tagId);

    PageResponse<?> getDetail(int pageNo, int pageSize);

    Object updateNews(List<ViewNewsRequest> requests);

    ViewNewsAndHotDetailDto getViewNewsAndHots(String language);

    List<TopicDto> getTopic(String language);

    List<TagEntity> getTag();

}
