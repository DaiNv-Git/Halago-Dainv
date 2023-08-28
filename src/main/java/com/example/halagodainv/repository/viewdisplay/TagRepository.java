package com.example.halagodainv.repository.viewdisplay;

import com.example.halagodainv.model.viewdisplayentity.TagEntity;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Integer> {

    List<TagEntity> findByIdIn(List<Integer> ids);
}
