package com.example.halagodainv.request.Influencer;

import com.example.halagodainv.request.SearchPageForm;
import lombok.Data;

@Data
public class InfluencerSearchRequest {
    private int pageSize = 10;
    private int pageNo = 1;
    private String name;
    private String industryName;
    private boolean fb;
    private boolean youtobe;
    private boolean titok;
    private boolean instagram;
    private Integer cityId;
    private Integer sex;
    private String birthday;
    private Double expense;

}
