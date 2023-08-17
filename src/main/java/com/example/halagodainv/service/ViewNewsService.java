package com.example.halagodainv.service;

import com.example.halagodainv.dto.viewnews.ViewNewsDetailDto;
import com.example.halagodainv.response.PageResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface ViewNewsService {
    PageResponse<?> getViewNews(int pageNo, int pageSize, String language);

    ViewNewsDetailDto getViewNewsDetail(int id, String language);

}
