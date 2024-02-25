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
    Optional<CampaignEntity> findByCampaignName(String name);
    @Query(nativeQuery = true, value = "select * from campaign cam where " +
            "cam.industry_id like concat('%',:industryId,'%') and " +
            "cam.communication like concat('%',:communication,'%') and " +
            "cam.campaign_name like concat('%',:campaignName,'%') order by cam.work_status asc,cam.id desc ")
    List<CampaignEntity> getByCampaigns(@Param("industryId") String industryId,
                                        @Param("communication") String communication,
                                        @Param("campaignName") String campaignName, Pageable pageable);

    Optional<CampaignEntity> findByIdAndIndustryId(int id, String industryId);

    @Query(nativeQuery = true, value = "select * from campaign cam where cam.id =:campaignId ")
    CampaignEntity findByCamId(@Param("campaignId") int campaignId);

    @Query(nativeQuery = true, value = "select count(*) from campaign cam where " +
            "cam.industry_id like concat('%',:industryId,'%') " +
            "and cam.communication like concat('%',:communication,'%') and " +
            "cam.campaign_name like concat('%',:campaignName,'%') ")
    int countAllBy(@Param("industryId") String industryId,
                   @Param("communication") String communication,
                   @Param("campaignName") String campaignName);
}
