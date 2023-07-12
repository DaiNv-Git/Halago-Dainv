package com.example.halagodainv.repository;


import com.example.halagodainv.model.NewsLanguage;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface NewsLanguageRepository extends JpaRepository<NewsLanguage,String> {
    @Modifying
    @Query(value = "DELETE FROM news_language WHERE id_news = ?1", nativeQuery = true)
    void deleteByIdNative(int id);
}
