package com.example.halagodainv.dto.campain;

import com.example.halagodainv.model.campaign.CampaignEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampaignRecruitment {
    private int campaignId;
    private String userName;
    private String campaignName;
}
