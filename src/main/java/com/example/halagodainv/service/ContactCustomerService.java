package com.example.halagodainv.service;


import com.example.halagodainv.request.concatcustomer.ConcatCustomerRequest;
import com.example.halagodainv.request.concatcustomer.FreeConsultationRequest;
import com.example.halagodainv.response.BaseResponse;

public interface ContactCustomerService {
    Object getListCustomers(int pageNo, int pageSize);

    Object add(ConcatCustomerRequest customerRequest);

    BaseResponse<?> addFreeConsul(FreeConsultationRequest request);


}
