package com.example.halagodainv.repository.campagin;

import com.example.halagodainv.model.campaign.CampaignRecruitmentLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CampaignRecruitmentLog extends JpaRepository<CampaignRecruitmentLogEntity, Integer> {


    Optional<CampaignRecruitmentLogEntity> findByIdInfluAndIdCampaign(int idInflu, int idCampaign);
}
