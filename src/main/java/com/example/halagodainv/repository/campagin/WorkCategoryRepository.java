package com.example.halagodainv.repository.campagin;

import com.example.halagodainv.model.campaign.WorkCategoryCampaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkCategoryRepository extends JpaRepository<WorkCategoryCampaign, Integer> {
}
