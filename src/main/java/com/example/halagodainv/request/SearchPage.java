package com.example.halagodainv.request;

import lombok.Data;

@Data
public class SearchPage {
    private int pageSize = 10;
    private int pageNo = 1;
    private String startDate = "1000-01-01";
    private String endDate = "9999-01-01";
}
