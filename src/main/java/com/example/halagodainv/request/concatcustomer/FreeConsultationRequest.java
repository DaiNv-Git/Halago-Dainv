package com.example.halagodainv.request.concatcustomer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FreeConsultationRequest {
    private String name;
    private String phone;
    private String email;
    private String note;
    private String website;
    private Boolean isAdvertisementVTC = false;
    private Boolean isBrandAmbassador = false;
    private Boolean isEvent = false;
    private Boolean isLiveStream = false;
    private Boolean isReview = false;
    private Boolean isOther = false;
}
