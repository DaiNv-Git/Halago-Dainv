package com.example.halagodainv.service;


import com.example.halagodainv.model.viewdisplayentity.FreeConsultationEntity;
import com.example.halagodainv.request.concatcustomer.ConcatCustomerRequest;
import com.example.halagodainv.request.concatcustomer.FreeConsultationRequest;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.response.PageResponse;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface ContactCustomerService {
    PageResponse<FreeConsultationEntity> getListCustomers(int pageNo, int pageSize);

    Object add(ConcatCustomerRequest customerRequest) throws MessagingException, UnsupportedEncodingException;

    BaseResponse<?> addFreeConsul(FreeConsultationRequest request) throws MessagingException, UnsupportedEncodingException;


}
