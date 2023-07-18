package com.example.halagodainv.service;


import com.example.halagodainv.request.concatcustomer.ConcatCustomerRequest;

public interface ContactCustomerService {
    Object getListCustomers(int pageNo, int pageSize);
    Object add(ConcatCustomerRequest customerRequest);


}
