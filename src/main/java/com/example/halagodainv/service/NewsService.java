package com.example.halagodainv.service;

import com.example.halagodainv.request.news.NewsAddRequest;
import com.example.halagodainv.request.news.NewsFormSearch;

public interface NewsService {
    Object getNews(NewsFormSearch newsSearch);

    Object getDetail(int newId);

    Object insertNews(NewsAddRequest newsAddRequest);

    Object update(NewsAddRequest newsAddRequest);

    Object delete(Integer id);


}
