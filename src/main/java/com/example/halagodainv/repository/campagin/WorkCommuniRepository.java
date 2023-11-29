package com.example.halagodainv.repository.campagin;

import com.example.halagodainv.model.campaign.WorkCommunicationCampaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkCommuniRepository extends JpaRepository<WorkCommunicationCampaign, Integer> {
}
