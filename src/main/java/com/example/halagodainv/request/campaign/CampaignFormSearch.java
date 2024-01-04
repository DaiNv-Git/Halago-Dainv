package com.example.halagodainv.request.campaign;

import com.example.halagodainv.request.SearchPageForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignFormSearch extends SearchPageForm {
    private String industryId = "";
    private String communicationId = "";
    private String campaginName = "";
    private String language = "vn";
}
