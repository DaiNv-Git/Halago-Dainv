package com.example.halagodainv.service;

import com.example.halagodainv.dto.viewnews.ViewNewsDetailDto;
import com.example.halagodainv.request.news.ViewNewsRequest;
import com.example.halagodainv.response.PageResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public interface ViewNewsService {
    PageResponse<?> getViewNews(int pageNo, int pageSize, String language, Long topicId);

    ViewNewsDetailDto getViewNewsDetail(int id, String language, Long topicId);

    PageResponse<?> getDetail(int pageNo, int pageSize);

    Object updateNews(List<ViewNewsRequest> requests);

}
