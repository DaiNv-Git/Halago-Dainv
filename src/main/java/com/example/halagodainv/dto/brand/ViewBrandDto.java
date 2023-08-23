package com.example.halagodainv.dto.brand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewBrandDto {
    private Long id;
    private String logoBrand;
    private String name;
    private Integer positionId;
    private String description;
}
