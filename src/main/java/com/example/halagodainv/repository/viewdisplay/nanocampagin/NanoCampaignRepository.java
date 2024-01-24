package com.example.halagodainv.repository.viewdisplay.nanocampagin;

import com.example.halagodainv.model.viewdisplayentity.nanocampaign.NanoCampaignEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NanoCampaignRepository extends JpaRepository<NanoCampaignEntity, Integer> {
}
