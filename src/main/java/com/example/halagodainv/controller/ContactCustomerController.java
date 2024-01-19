package com.example.halagodainv.controller;

import com.example.halagodainv.model.viewdisplayentity.FreeConsultationEntity;
import com.example.halagodainv.request.concatcustomer.ConcatCustomerRequest;
import com.example.halagodainv.request.concatcustomer.FreeConsultationRequest;
import com.example.halagodainv.response.PageResponse;
import com.example.halagodainv.service.ContactCustomerService;
import io.swagger.models.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/contact-customer")
public class ContactCustomerController {
    @Autowired
    private ContactCustomerService contactCustomerService;

    Logger logger = LoggerFactory.getLogger(ContactCustomerController.class);

    @GetMapping("")
    public ResponseEntity<PageResponse<FreeConsultationEntity>> getALL(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                                                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(contactCustomerService.getListCustomers(pageNo, pageSize));
    }

    @PostMapping("/add")
    public ResponseEntity<Object> getALL(@RequestBody FreeConsultationRequest request) throws MessagingException, UnsupportedEncodingException {
        return ResponseEntity.ok(contactCustomerService.add(request));
    }

    @PostMapping("/add-free-consul")
    public ResponseEntity<Object> save(@RequestBody FreeConsultationRequest customerRequest) throws MessagingException, UnsupportedEncodingException {
        return ResponseEntity.ok(contactCustomerService.addFreeConsul(customerRequest));
    }

}
