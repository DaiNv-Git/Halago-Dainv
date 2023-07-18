package com.example.halagodainv.request.Influencer;

import com.example.halagodainv.request.SearchPageForm;
import lombok.Data;

@Data
public class InfluencerSearchRequest extends SearchPageForm {
    private String name;
    private String industryName;
    private boolean fb;
    private boolean youtobe;
    private boolean titok;
    private boolean instagram;
}
