package com.example.halagodainv.repository;

import com.example.halagodainv.model.InfluencerDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface InfluencerDetailRepository extends JpaRepository<InfluencerDetailEntity, Long> {

    @Modifying
    @Transactional
    void deleteByInfluId(long influId);
}
