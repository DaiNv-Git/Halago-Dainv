package com.example.halagodainv.service;

import com.example.halagodainv.dto.media.MediaListDto;
import com.example.halagodainv.model.viewdisplayentity.media.MediaDetail;
import com.example.halagodainv.model.viewdisplayentity.media.MediaListImg;
import com.example.halagodainv.request.media.MediaImageRequest;
import com.example.halagodainv.request.media.MediaRequest;

import java.util.List;

public interface MediaService {
    List<MediaDetail> getMedia();

    List<MediaListDto> getMediaList();

    List<MediaDetail> getDetail();

    List<MediaListImg> getMediaImgDetail();

    void updateMedia(List<MediaRequest> mediaRequests);

    void updateMediaBlockHide(MediaRequest request);

    void updateImgList(List<MediaImageRequest> imageRequests);
}
