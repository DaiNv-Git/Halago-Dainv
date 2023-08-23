package com.example.halagodainv.request.brand;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
@Data
public class VieBrandRequest {
    private Long id;
    private String logoBrand;
    private String name;
    private Integer positionId;
    private String descriptionVN;
    private String descriptionEN;
}
