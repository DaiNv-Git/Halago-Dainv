package com.example.halagodainv.repository;

import com.example.halagodainv.dto.about.AboutDto;
import com.example.halagodainv.model.AboutUsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AboutUsRepository extends CrudRepository<AboutUsEntity, Integer> {
    @Query("select new com.example.halagodainv.dto.about.AboutDto(a.id,a.content,al.contentEn,a.title,al.titleEn,al.language) from AboutUsEntity a left join " +
            "AboutUsLanguageEntity al on a.id = al.idAbout where a.id =:id ")
    AboutDto getByAbout(@Param("id") int id);

    @Query("select new com.example.halagodainv.dto.about.AboutDto(a.id,a.content,al.contentEn,a.title,al.titleEn,al.language) from AboutUsEntity a left join " +
            "AboutUsLanguageEntity al on a.id = al.idAbout")
    AboutDto getByAbout();

    @Query("select a from AboutUsEntity a left join " +
            "AboutUsLanguageEntity al on a.id = al.idAbout where a.id =:id and al.idLang =:id")
    AboutUsEntity findById(@Param("id") int id);


}
