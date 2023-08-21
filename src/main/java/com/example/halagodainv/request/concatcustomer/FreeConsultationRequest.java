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
    private Boolean isAdvertisementVTC;
    private Boolean isBrandAmbassador;
    private Boolean isEvent;
    private Boolean isLiveStream;
    private Boolean isReview;
    private Boolean isOther;
}
