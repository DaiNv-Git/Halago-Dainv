package com.example.halagodainv.service;

import com.example.halagodainv.model.viewdisplayentity.media.MediaDetail;
import com.example.halagodainv.request.media.MediaRequest;

import java.util.List;

public interface MediaService {
    List<MediaDetail> getMedia();

    List<MediaDetail> getDetail();

    void updateMedia(List<MediaRequest> mediaRequests);
}
