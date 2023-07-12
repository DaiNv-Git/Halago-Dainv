package com.example.halagodainv.repository;

import com.example.halagodainv.model.CampaignEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<CampaignEntity, Integer> {
    @Query(nativeQuery = true, value = "select * from campaign cam where cam.campaign_name like concat('%',:campaignName,'%') " +
            "and (cam.created >=  STR_TO_DATE(:startDate, '%Y-%m-%d %H:%i:%s') AND " +
            "cam.created <=  STR_TO_DATE(:endDate, '%Y-%m-%d %H:%i:%s'))ORDER BY cam.id DESC ")
    List<CampaignEntity> getByCampaigns(@Param("campaignName") String campaignName, @Param("startDate") String startDate, @Param("endDate") String endDate, Pageable pageable);
    @Query(nativeQuery = true, value = "select * from campaign cam where cam.id =:campaignId ")
    CampaignEntity findByCamId(@Param("campaignId") int campaignId);

    int countAllBy();
}
