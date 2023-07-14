package com.example.halagodainv.controller;

import com.example.halagodainv.service.ContactCustomerService;
import io.swagger.models.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact-customer")
public class ContactCustomerController {
    @Autowired
    private ContactCustomerService contactCustomerService;

    Logger logger = LoggerFactory.getLogger(ContactCustomerController.class);

    @GetMapping("")
    public ResponseEntity<Object> getALL(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(contactCustomerService.getListCustomers(pageNo, pageSize));
    }

}
