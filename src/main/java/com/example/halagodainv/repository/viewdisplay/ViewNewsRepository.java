package com.example.halagodainv.repository.viewdisplay;

import com.example.halagodainv.dto.viewnews.ViewNewsMap;
import com.example.halagodainv.model.viewdisplayentity.ViewNewsEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewNewsRepository extends JpaRepository<ViewNewsEntity, Integer> {

    @Query("select new com.example.halagodainv.dto.viewnews.ViewNewsMap(n.id,n.title,n.herder,n.body,n.footer,n.image1,n.image2,n.titleEN,n.herderEN,n.bodyEN,n.footerEN,n.createDate,n.topicId) from  ViewNewsEntity n " +
            "where :topicId = 0 or n.topicId=:topicId")
    List<ViewNewsMap> getAllTopic(@Param("topicId") Long topicId);

    @Query("select new com.example.halagodainv.dto.viewnews.ViewNewsMap(n.id,n.title,n.herder,n.body,n.footer,n.image1,n.image2,n.titleEN,n.herderEN,n.bodyEN,n.footerEN,n.createDate,n.topicId) from  ViewNewsEntity n ")
    List<ViewNewsMap> getALl(Pageable pageable);

    @Query("select new com.example.halagodainv.dto.viewnews.ViewNewsMap(n.id,n.title,n.herder,n.body,n.footer,n.image1,n.image2,n.titleEN,n.herderEN,n.bodyEN,n.footerEN,n.createDate,n.topicId) from  ViewNewsEntity n " +
            "where n.id =:id and (:topicId = 0 or n.topicId=:topicId)")
    ViewNewsMap findByViewNewId(@Param("id") Integer id, @Param("topicId") Long topicId);
}
