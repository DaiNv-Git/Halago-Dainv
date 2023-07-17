package com.example.halagodainv.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchPageForm {
    private int pageSize = 10;
    private int pageNo = 1;
    private String startDate = "1000-01-01";
    private String endDate = "9999-01-01";
}
