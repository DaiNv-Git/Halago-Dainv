package com.example.halagodainv.request.campaign;

import com.example.halagodainv.request.SearchPage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignSearch extends SearchPage {
    private String campaignName = "";
}
