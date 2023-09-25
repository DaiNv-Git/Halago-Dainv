package com.example.halagodainv.dto.hompage;

import com.example.halagodainv.model.EfficiencyOptimizationEntity;
import com.example.halagodainv.model.viewdisplayentity.HomepageEntitty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomePageDetail {
    private String domesticBrands;
    private String foreignBrands;
    private String successfulCampaign;
    private String kOLsInformational;
    private String title;

    public HomePageDetail(List<HomepageEntitty> entitty, String language) {
        entitty.stream().forEach(i -> {
            this.domesticBrands = i.getDomesticBrands();
            this.foreignBrands = i.getForeignBrands();
            this.successfulCampaign = i.getSuccessfulCampaign();
            this.kOLsInformational = i.getKOLsInformational();
            if (language.toUpperCase().equals("VN")) {
                this.title = i.getTitleVN();
            } else if (language.toUpperCase().equals("EN")) {
                this.title = i.getTitleEN();
            }
        });

    }
}
