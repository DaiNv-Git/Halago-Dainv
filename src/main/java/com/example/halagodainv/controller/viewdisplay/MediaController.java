package com.example.halagodainv.controller.viewdisplay;

import com.example.halagodainv.dto.media.MediaListDto;
import com.example.halagodainv.model.viewdisplayentity.media.MediaDetail;
import com.example.halagodainv.model.viewdisplayentity.media.MediaListImg;
import com.example.halagodainv.request.media.MediaImageRequest;
import com.example.halagodainv.request.media.MediaRequest;
import com.example.halagodainv.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @GetMapping("/media")
    public List<MediaDetail> getMedia() {
        return mediaService.getMedia();
    }

    @GetMapping("/media-detail")
    public List<MediaDetail> getMediaDetail() {
        return mediaService.getDetail();
    }

    @PostMapping("/media-update")
    public Object updateMedia(@RequestBody List<MediaRequest> mediaRequests) {
        if (CollectionUtils.isEmpty(mediaRequests)) {
            throw new RuntimeException("update faild");
        }
        mediaService.updateMedia(mediaRequests);
        return "update success";
    }

    @PostMapping("/media-update-block-hide")
    public Object updateMedia(@RequestBody MediaRequest mediaRequests) {
        mediaService.updateMediaBlockHide(mediaRequests);
        return "update success";
    }

    @GetMapping("/img-list")
    public List<MediaListDto> getImageList() {
        return mediaService.getMediaList();
    }

    @GetMapping("/img-list-detail")
    public List<MediaListImg> getImageListDetail() {
        return mediaService.getMediaImgDetail();
    }

    @PostMapping("/img-update")
    public String updateImgList(@RequestBody List<MediaImageRequest> mediaImageRequests) {
        mediaService.updateImgList(mediaImageRequests);
        return "success";
    }
}
