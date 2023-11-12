package com.example.halagodainv.request.concatcustomer;

import lombok.Data;

import javax.validation.constraints.Max;

@Data
public class ConcatCustomerRequest {
    private String phone;
    private String email;
    private String userName;
    private String product;
    private String note;
}
