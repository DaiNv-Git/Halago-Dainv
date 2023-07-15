package com.example.halagodainv.service;

import com.example.halagodainv.request.page.PageAddRequest;
import com.example.halagodainv.request.page.PageEditRequest;
import com.example.halagodainv.request.page.PageSearch;

public interface PageService {
    Object getPageAll(PageSearch pageSearch);
    Object detail(long pageId);

    Object add(PageAddRequest pageAddRequest);

    Object edit(PageEditRequest pageEditRequest);

    Object delete(long pageId);
}
