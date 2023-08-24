package com.example.halagodainv.request.homepage;

import lombok.Data;

import java.util.List;

@Data
public class HomePageRequest {
    private String titleCreativeVN;
    private String contentCreativeVN;
    private String titleCreativeEN;
    private String contentCreativeEN;

    private String titleEfficiencyOptimizationsVN;
    private String contentEfficiencyOptimizationsVN;
    private String titleEfficiencyOptimizationsEN;
    private String contentEfficiencyOptimizationsEN;
    List<HomeUpdateRequest> advantages;
}
