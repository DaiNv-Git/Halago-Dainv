package com.example.halagodainv.repository;


import com.example.halagodainv.model.FooterLanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FooterLanguageRepository extends JpaRepository<FooterLanguageEntity,Integer> {
    @Query("Select lang from FooterLanguageEntity lang where lang.idFooter=:id")
    FooterLanguageEntity findFooterLanguage(@Param("id")int id);
}
