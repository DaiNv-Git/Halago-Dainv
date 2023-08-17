package com.example.halagodainv.repository.viewdisplay;

import com.example.halagodainv.dto.viewnews.ViewNewsMap;
import com.example.halagodainv.model.viewdisplayentity.ViewNewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewNewsRepository extends JpaRepository<ViewNewsEntity, Integer> {

    @Query("select new com.example.halagodainv.dto.viewnews.ViewNewsMap(n.id,n.title,n.herder,n.body,n.footer,n.image1,n.image2,el.titleEN,el.herderEN,el.bodyEN,el.footerEN,n.createDate) from  ViewNewsEntity n " +
            "left join ViewNewsLanguageEntity el on el.newViewId =n.id ")
    List<ViewNewsMap> getALl();

    @Query("select new com.example.halagodainv.dto.viewnews.ViewNewsMap(n.id,n.title,n.herder,n.body,n.footer,n.image1,n.image2,el.titleEN,el.herderEN,el.bodyEN,el.footerEN,n.createDate) from  ViewNewsEntity n " +
            "left join ViewNewsLanguageEntity el on el.newViewId =n.id where n.id =:id ")
    ViewNewsMap findByViewNewId(@Param("id") Integer id);
}
