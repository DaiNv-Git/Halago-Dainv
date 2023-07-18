package com.example.halagodainv.service;


import com.example.halagodainv.request.about.AboutEditRequest;

public interface AboutUsService {
    public Object getDetail();

    Object update(AboutEditRequest aboutEditRequest);

}
