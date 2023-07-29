package com.example.halagodainv.controller;

import com.example.halagodainv.exception.GeneralException;
import com.example.halagodainv.request.concatcustomer.ConcatCustomerRequest;
import com.example.halagodainv.request.homepage.HomePageRequest;
import com.example.halagodainv.service.ContactCustomerService;
import com.example.halagodainv.service.HomePageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/homepage")
public class HomePage {
    private final HomePageService homePageService;

    @Autowired
    private ContactCustomerService contactCustomerService;

    @PostMapping("")
    public ResponseEntity<Object> getAll(@RequestParam("language") String language) throws GeneralException {
        return ResponseEntity.ok(homePageService.getHomePage(language));
    }

    @PostMapping("/partner")
    public ResponseEntity<Object> getAll(@RequestParam(value = "partnerId", defaultValue = "1", required = false) int partnerId) throws GeneralException {
        return ResponseEntity.ok(homePageService.getPartner(partnerId));
    }

    @PostMapping("/detail")
    public ResponseEntity<Object> detail() throws GeneralException {
        return ResponseEntity.ok(homePageService.getDetail());
    }

    @PostMapping("/edit")
    public ResponseEntity<Object> edit(@RequestBody HomePageRequest homePageRequest) throws GeneralException {
        return ResponseEntity.ok(homePageService.updateHomePage(homePageRequest));
    }

    @PostMapping("/add-contact")
    public ResponseEntity<Object> getALL(@RequestBody ConcatCustomerRequest customerRequest) {
        return ResponseEntity.ok(contactCustomerService.add(customerRequest));
    }

}
