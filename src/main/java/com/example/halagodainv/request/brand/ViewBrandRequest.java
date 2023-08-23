package com.example.halagodainv.request.brand;

import lombok.Data;

@Data
public class ViewBrandRequest {
    private Long id;
    private String logoBrand;
    private String name;
    private Integer positionId;
    private String descriptionVN;
    private String descriptionEN;
}
