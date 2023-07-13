package com.example.halagodainv.repository;


import com.example.halagodainv.model.NewsLanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsLanguageRepository extends JpaRepository<NewsLanguageEntity,String> {
    @Modifying
    @Query(value = "DELETE FROM news_language WHERE id_news = ?1", nativeQuery = true)
    void deleteByIdNative(int id);
}
