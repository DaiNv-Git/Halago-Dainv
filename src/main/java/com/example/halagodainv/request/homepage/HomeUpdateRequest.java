package com.example.halagodainv.request.homepage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeUpdateRequest {
    private String DomesticBrands;
    private String ForeignBrands;
    private String SuccessfulCampaign;
    private String KOLsInformational;
    private String titleVN;
    private String titleEN;
}
