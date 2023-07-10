package com.example.halagodainv.repository;


import com.example.halagodainv.model.FooterLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FooterLanguageRepository extends JpaRepository<FooterLanguage,String> {
//    @Query("Select lang from FooterLanguage lang where lang.idFooter=:id")
//    FooterLanguage findFooterLanguage(@Param("id")int id);
}
