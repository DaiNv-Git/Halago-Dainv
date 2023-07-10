package com.example.halagodainv.repository;


import com.example.halagodainv.model.InfluencerPortalLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfluencerPortalLanguageRepository extends JpaRepository<InfluencerPortalLanguage,String> {
//    @Query("Select lang from InfluencerPortalLanguage lang where lang.idInfluencerPortal=:id ")
//    InfluencerPortalLanguage findInfluencerPortalLanguage(@Param("id")int id);
}
