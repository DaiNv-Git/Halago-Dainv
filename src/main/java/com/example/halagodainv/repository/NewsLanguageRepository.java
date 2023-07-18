package com.example.halagodainv.repository;


import com.example.halagodainv.model.NewsLanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface NewsLanguageRepository extends JpaRepository<NewsLanguageEntity,String> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "delete from news_language where new_id =:newId")
    void deleteByNewId(@Param("newId") int newId);
}
