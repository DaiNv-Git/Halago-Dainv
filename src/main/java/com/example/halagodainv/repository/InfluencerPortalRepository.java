package com.example.halagodainv.repository;

import com.example.halagodainv.model.InfluencerPortal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfluencerPortalRepository extends JpaRepository<InfluencerPortal, String> {

//    @Query("Select portal from InfluencerPortal portal where portal.id=:id")
//    InfluencerPortal findInfluencerPortalById(@Param("id") int id);


}
