package com.example.halagodainv.repository;


import com.example.halagodainv.model.NewsLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsLanguageRepository extends JpaRepository<NewsLanguage,String> {
}
