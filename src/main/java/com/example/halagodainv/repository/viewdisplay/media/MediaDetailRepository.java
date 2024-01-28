package com.example.halagodainv.repository.viewdisplay.media;

import com.example.halagodainv.model.viewdisplayentity.media.MediaDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MediaDetailRepository extends JpaRepository<MediaDetail, Long> {

    Optional<MediaDetail> findByType(int type);
}
