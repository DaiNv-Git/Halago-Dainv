package com.example.halagodainv.repository.campagin;

import com.example.halagodainv.model.campaign.CampaignEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<CampaignEntity, Integer> {
    @Query(nativeQuery = true, value = "select * from campaign cam where cam.industry_id like concat('%',:industryId,'%') and cam.communication like concat('%',:communicationId,'%') ")
    List<CampaignEntity> getByCampaigns(@Param("industryId") String industryId, @Param("communicationId") String communicationId, Pageable pageable);

    @Query(nativeQuery = true, value = "select * from campaign cam where " +
            "(FIND_IN_SET('1', :industryId) > 0 " +
            "or FIND_IN_SET('2', :industryId) > 0 " +
            "or FIND_IN_SET('3', :industryId) > 0 " +
            "or FIND_IN_SET('4', :industryId) > 0 " +
            "or FIND_IN_SET('5', :industryId) > 0 " +
            "or FIND_IN_SET('6', :industryId) > 0 " +
            "or FIND_IN_SET('7', :industryId) > 0 " +
            "or FIND_IN_SET('8', :industryId) > 0 " +
            "or FIND_IN_SET('9', :industryId) > 0 " +
            "or FIND_IN_SET('10', :industryId) > 0 " +
            "or FIND_IN_SET('11', :industryId) > 0 " +
            "or FIND_IN_SET('12', :industryId) > 0 " +
            "or FIND_IN_SET('13', :industryId) > 0) and cam.id <> :camId and cam.work_status = :workStatus limit 10 ")
    List<CampaignEntity> getByRelateToCampaigns(@Param("industryId") String industryId,@Param("camId") int camId,@Param("workStatus") int workStatus);

    @Query(nativeQuery = true, value = "select * from campaign cam where cam.id =:campaignId ")
    CampaignEntity findByCamId(@Param("campaignId") int campaignId);

    @Query(nativeQuery = true, value = "select count(*) from campaign cam where cam.industry_id like concat('%',:industryId,'%') and cam.communication like concat('%',:communicationId,'%') ")
    int countAllBy(@Param("industryId") String industryId, @Param("communicationId") String communicationId);
}
