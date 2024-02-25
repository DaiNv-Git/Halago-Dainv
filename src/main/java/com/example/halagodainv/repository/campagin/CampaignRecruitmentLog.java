package com.example.halagodainv.repository.campagin;

import com.example.halagodainv.model.campaign.CampaignRecruitmentLogEntity;
import com.example.halagodainv.response.CampaignUserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CampaignRecruitmentLog extends JpaRepository<CampaignRecruitmentLogEntity, Integer> {
    Optional<CampaignRecruitmentLogEntity> findByIdInfluAndIdCampaign(int idInflu, int idCampaign);
   void deleteByIdCampaignAndIdInflu( int idCampaign,int idInflu);
}
