package com.example.halagodainv.service;

import com.example.halagodainv.dto.news.NewRelationTopicDto;
import com.example.halagodainv.dto.topic.TopicDto;
import com.example.halagodainv.dto.viewnews.ViewNewsAndHotDetailDto;
import com.example.halagodainv.dto.viewnews.ViewNewsDetailDto;
import com.example.halagodainv.exception.GeneralException;
import com.example.halagodainv.model.viewdisplayentity.TagEntity;
import com.example.halagodainv.request.news.NewsAddRequest;
import com.example.halagodainv.request.news.NewsFormSearch;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.response.PageResponse;

import java.util.List;

public interface NewsService {
    BaseResponse<?> getNews(NewsFormSearch newsSearch);

    Object getDetail(int newId);

    Object insertNews(NewsAddRequest newsAddRequest);

    Object update(NewsAddRequest newsAddRequest);
    Object update(List<NewsAddRequest> newsAddRequest);

    Object delete(Integer id);

    PageResponse<?> getViewNews(int pageNo, int pageSize, String language, Long topicId, Long tagId);

    ViewNewsDetailDto getViewNewsDetail(int id, String language);

    ViewNewsAndHotDetailDto getViewNewsAndHots(String language);

    List<TopicDto> getTopic(String language);

    List<TagEntity> getTag();

    void setIsHot(int idNew) throws GeneralException;

    void setIsNotHot(int idNew) throws GeneralException;

    List<NewRelationTopicDto> getNewRelationTopics(int topicId, int newId,String language);
}
