package com.example.halagodainv.repository.viewdisplay.media;

import com.example.halagodainv.model.viewdisplayentity.media.MediaListImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaListRepository extends JpaRepository<MediaListImg, Integer> {
}
