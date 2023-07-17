package com.example.halagodainv.repository;

import com.example.halagodainv.model.SocialNetworkInfluencer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SocialNetworkInfluencerRepository extends JpaRepository<SocialNetworkInfluencer, Integer> {

        @Modifying
        @Query(value = "DELETE FROM social_network_influencer WHERE influencerID = ?1", nativeQuery = true)
        void deleteByInfluencerId(Integer influencerId);

}
