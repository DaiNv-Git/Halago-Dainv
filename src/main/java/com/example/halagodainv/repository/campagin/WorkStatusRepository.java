package com.example.halagodainv.repository.campagin;

import com.example.halagodainv.model.campaign.WorkStatusCampaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkStatusRepository extends JpaRepository<WorkStatusCampaign, Integer> {
}
