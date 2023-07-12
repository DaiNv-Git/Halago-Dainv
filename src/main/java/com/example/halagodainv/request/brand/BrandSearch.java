package com.example.halagodainv.request.brand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandSearch {
    private int pageSize = 10;
    private int pageNo = 1;
    private String brandName = "";
    private String startDate = "1000-01-01";
    private String endDate = "9999-01-01";
}
