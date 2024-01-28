package com.example.halagodainv.service.impl.viewdisplay;

import com.example.halagodainv.model.viewdisplayentity.media.MediaDetail;
import com.example.halagodainv.repository.viewdisplay.media.MediaDetailRepository;
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
import java.util.List;
import java.util.Optional;

@Service
public class MediaServiceImpl implements MediaService {
    @Autowired
    private MediaDetailRepository mediaDetailRepository;
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
        List<MediaDetail> mediaDetails  = nativeQuery.getResultList();
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

}
