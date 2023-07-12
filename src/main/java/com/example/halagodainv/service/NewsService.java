package com.example.halagodainv.service;

import com.example.halagodainv.model.News;
import com.example.halagodainv.request.news.NewsAddRequest;

import java.util.List;

public interface NewsService {

    Object insertNews(NewsAddRequest newsAddRequest);
    Object update(NewsAddRequest newsAddRequest);
    Object delete(Integer id);


}
