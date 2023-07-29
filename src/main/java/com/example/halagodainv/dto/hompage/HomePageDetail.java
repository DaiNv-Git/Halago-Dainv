package com.example.halagodainv.dto.hompage;

import com.example.halagodainv.model.AdvantageEntity;
import com.example.halagodainv.model.EfficiencyOptimizationEntity;
import com.example.halagodainv.request.homepage.AdvantageRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomePageDetail {
    private String titleCreativeVN;
    private String contentCreativeVN;
    private String titleCreativeEN;
    private String contentCreativeEN;

    private String titleEfficiencyOptimizationsVN;
    private String contentEfficiencyOptimizationsVN;
    private String titleEfficiencyOptimizationsEN;
    private String contentEfficiencyOptimizationsEN;
    List<AdvantageMapEntityDto> advantages = new ArrayList<>();

    public HomePageDetail(List<EfficiencyOptimizationEntity> efficiencyOptimizationDtos, List<EfficiencyOptimizationEntity> Creatives, List<AdvantageMapEntityDto> advantages) {
        efficiencyOptimizationDtos.forEach(eff -> {
            if (eff.getLanguage().equals("VN")) {
                this.titleEfficiencyOptimizationsVN = eff.getTitle();
                this.contentEfficiencyOptimizationsVN = eff.getContent();
            } else if (eff.getLanguage().equals("EN")) {
                this.titleEfficiencyOptimizationsEN = eff.getTitle();
                this.contentEfficiencyOptimizationsEN = eff.getContent();
            }
        });

        Creatives.forEach(Creative -> {
            if (Creative.getLanguage().equals("VN")) {
                this.titleCreativeVN = Creative.getTitle();
                this.contentCreativeVN = Creative.getContent();
            } else if (Creative.getLanguage().equals("EN")) {
                this.titleCreativeEN = Creative.getTitle();
                this.contentCreativeEN = Creative.getContent();
            }
        });
        this.advantages = advantages;
    }
}
