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

    @Query("select new com.example.halagodainv.dto.viewnews.ViewNewsMap(n.id,n.title,n.herder,n.body,n.footer,n.image1,n.image2,n.titleEN,n.herderEN,n.bodyEN,n.footerEN,n.createDate,n.topicId,n.tagId) from  ViewNewsEntity n " +
            "where (:#{#topicId} = 0l or n.topicId=:#{#topicId}) and (:#{#tagId} = 0l or n.tagId=:#{#tagId}) ")
    List<ViewNewsMap> getAllTopic(@Param("topicId") Long topicId, @Param("tagId") Long tagId);

    @Query("select new com.example.halagodainv.dto.viewnews.ViewNewsMap(n.id,n.title,n.herder,n.body,n.footer,n.image1,n.image2,n.titleEN,n.herderEN,n.bodyEN,n.footerEN,n.createDate,n.topicId,n.tagId) from  ViewNewsEntity n ")
    List<ViewNewsMap> getALl(Pageable pageable);

    @Query("select new com.example.halagodainv.dto.viewnews.ViewNewsMap(n.id,n.title,n.herder,n.body,n.footer,n.image1,n.image2,n.titleEN,n.herderEN,n.bodyEN,n.footerEN,n.createDate,n.topicId,n.tagId) from  ViewNewsEntity n " +
            "where n.id =:id and (:topicId = 0l or n.topicId=:topicId) and (:tagId = 0l or n.tagId=:tagId)")
    ViewNewsMap findByViewNewId(@Param("id") Integer id, @Param("topicId") Long topicId, @Param("tagId") Long tagId);

    @Query("select new com.example.halagodainv.dto.viewnews.ViewNewsMap(n.id,n.title,n.herder,n.body,n.footer,n.image1,n.image2,n.titleEN,n.herderEN,n.bodyEN,n.footerEN,n.createDate,n.topicId,n.tagId) from  ViewNewsEntity n " +
            "order by n.id")
    List<ViewNewsMap> getNews(Pageable pageable);

    @Query("select new com.example.halagodainv.dto.viewnews.ViewNewsMap(n.id,n.title,n.herder,n.body,n.footer,n.image1,n.image2,n.titleEN,n.herderEN,n.bodyEN,n.footerEN,n.createDate,n.topicId,n.tagId) from  ViewNewsEntity n " +
            "where n.isHot = true order by n.id")
    List<ViewNewsMap> getNewHots(Pageable pageable);
}
