package com.example.halagodainv.service.impl;


import com.example.halagodainv.model.ContactCustomerEntity;
import com.example.halagodainv.repository.ContactCustomerRepository;
import com.example.halagodainv.request.concatcustomer.ConcatCustomerRequest;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.response.PageResponse;
import com.example.halagodainv.service.ContactCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerContactServiceImpl implements ContactCustomerService {
    private final ContactCustomerRepository contactCustomerRepository;

    public Object getListCustomers(int pageNo, int pageSize) {
        int offset = 0;
        if (pageNo > 0) {
            offset = pageNo - 1;
        }
        Pageable pageable = PageRequest.of(offset, pageSize);
        List<ContactCustomerEntity> entities = contactCustomerRepository.findAll(PageRequest.of(offset, pageSize, Sort.Direction.DESC, "id")).toList();
        int totalCount = (int) contactCustomerRepository.count();
        if (CollectionUtils.isEmpty(entities)) {
            PageResponse response = new PageResponse<>(new PageImpl<>(entities, pageable, 0));
            return response;
        }
        PageResponse response = new PageResponse<>(new PageImpl<>(entities, pageable, totalCount));
        return response;
    }

    public Object add(ConcatCustomerRequest customerRequest) {
        ContactCustomerEntity contactCustomerEntity = new ContactCustomerEntity();
        contactCustomerEntity.setCreated(new Date());
        contactCustomerEntity.setEmail(customerRequest.getEmail());
        contactCustomerEntity.setPhone("0"+customerRequest.getPhone());
        contactCustomerEntity.setName(customerRequest.getUserName());
        contactCustomerEntity.setProduct(customerRequest.getProduct());
        contactCustomerEntity.setNote(customerRequest.getNote());
        contactCustomerRepository.save(contactCustomerEntity);
        return new BaseResponse<>(HttpStatus.OK.value(), "Đăng ký thành công", null);
    }

}
