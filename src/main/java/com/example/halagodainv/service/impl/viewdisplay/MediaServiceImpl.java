package com.example.halagodainv.service.impl.viewdisplay;

import com.example.halagodainv.dto.media.MediaListDto;
import com.example.halagodainv.model.viewdisplayentity.media.MediaDetail;
import com.example.halagodainv.model.viewdisplayentity.media.MediaListImg;
import com.example.halagodainv.repository.viewdisplay.media.MediaDetailRepository;
import com.example.halagodainv.repository.viewdisplay.media.MediaListRepository;
import com.example.halagodainv.request.media.MediaImageRequest;
import com.example.halagodainv.request.media.MediaRequest;
import com.example.halagodainv.service.MediaService;
import com.example.halagodainv.until.FileImageUtil;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MediaServiceImpl implements MediaService {
    @Autowired
    private MediaDetailRepository mediaDetailRepository;
    @Autowired
    private MediaListRepository mediaListRepository;
    @Autowired
    private FileImageUtil fileImageUtil;

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;


    public List<MediaDetail> getMedia() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM media_detail a WHERE a.is_show = true ");
        Query nativeQuery = entityManager.createNativeQuery(stringBuilder.toString(), MediaDetail.class);
        List<MediaDetail> mediaDetails = nativeQuery.getResultList();
        return mediaDetails;
    }


    public List<MediaDetail> getDetail() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM media_detail a ");
        Query nativeQuery = entityManager.createNativeQuery(stringBuilder.toString(), MediaDetail.class);
        List<MediaDetail> mediaDetails = nativeQuery.getResultList();
        return mediaDetails;
    }

    @Transactional
    public void updateMedia(List<MediaRequest> mediaRequests) {
        for (MediaRequest request : mediaRequests) {
            Optional<MediaDetail> mediaDetail = mediaDetailRepository.findByType(request.getType());
            if (mediaDetail.isPresent()) {
                mediaDetail.get().setShow(request.isShow());
                mediaDetail.get().setImage(fileImageUtil.uploadImage(request.getImage()));
                mediaDetail.get().setType(request.getType());
                mediaDetailRepository.save(mediaDetail.get());
            }
        }
    }

    @Transactional
    public void updateMediaBlockHide(MediaRequest request) {
        Optional<MediaDetail> mediaDetail = mediaDetailRepository.findByType(request.getType());
        if (mediaDetail.isPresent()) {
            mediaDetail.get().setShow(request.isShow());
            mediaDetail.get().setImage(fileImageUtil.uploadImage(request.getImage()));
            mediaDetail.get().setType(request.getType());
            mediaDetailRepository.save(mediaDetail.get());
        }
    }


    public List<MediaListDto> getMediaList() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT img FROM media_list ORDER BY IFNULL(SUBSTRING_INDEX(name_file, '_', -1),'') DESC ");
        Query nativeQuery = entityManager.createNativeQuery(stringBuilder.toString());
        return nativeQuery.unwrap(NativeQuery.class).setResultTransformer(Transformers.aliasToBean(MediaListDto.class)).getResultList();
    }

    public List<MediaListImg> getMediaImgDetail() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM media_list media_list ORDER BY IFNULL(SUBSTRING_INDEX(name_file, '_', -1),'') DESC ");
        Query nativeQuery = entityManager.createNativeQuery(stringBuilder.toString(), MediaListImg.class);
        List<MediaListImg> mediaListImgs = nativeQuery.getResultList();
        return mediaListImgs;
    }

    public void updateImgList(List<MediaImageRequest> imageRequests) {
        mediaListRepository.deleteAll();
        List<MediaListImg> imgs = new ArrayList<>();
        imageRequests.forEach(x -> imgs.add(new MediaListImg(x.getImg(), x.getNameFile())));
        mediaListRepository.saveAll(imgs);
    }
}
