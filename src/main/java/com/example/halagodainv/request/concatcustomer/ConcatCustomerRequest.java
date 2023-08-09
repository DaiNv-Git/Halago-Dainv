package com.example.halagodainv.request.concatcustomer;

import lombok.Data;

@Data
public class ConcatCustomerRequest {
    private long phone;
    private String email;
    private String userName;
    private String product;
    private String note;
}
