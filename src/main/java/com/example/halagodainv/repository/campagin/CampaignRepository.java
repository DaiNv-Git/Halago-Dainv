package com.example.halagodainv.repository.campagin;

import com.example.halagodainv.model.campaign.CampaignEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Repository
public interface CampaignRepository extends JpaRepository<CampaignEntity, Integer> {
    @Query(nativeQuery = true, value = "select * from campaign cam where cam.industry_id like concat('%',:industryId,'%') and cam.communication like concat('%',:communicationId,'%') ")
    List<CampaignEntity> getByCampaigns(@Param("industryId") String industryId, @Param("communicationId") String communicationId, Pageable pageable);

    Optional<CampaignEntity> findByIdAndIndustryId(int id, String industryId);

    @Query(nativeQuery = true, value = "select * from campaign cam where cam.id =:campaignId ")
    CampaignEntity findByCamId(@Param("campaignId") int campaignId);

    @Query(nativeQuery = true, value = "select count(*) from campaign cam where cam.industry_id like concat('%',:industryId,'%') and cam.communication like concat('%',:communicationId,'%') ")
    int countAllBy(@Param("industryId") String industryId, @Param("communicationId") String communicationId);
}
