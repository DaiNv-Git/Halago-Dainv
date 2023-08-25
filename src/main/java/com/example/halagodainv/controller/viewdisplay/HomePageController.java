package com.example.halagodainv.controller.viewdisplay;

import com.example.halagodainv.exception.GeneralException;
import com.example.halagodainv.request.concatcustomer.ConcatCustomerRequest;
import com.example.halagodainv.request.homepage.HomeUpdateRequest;
import com.example.halagodainv.request.homepage.PartnerRequest;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.service.ContactCustomerService;
import com.example.halagodainv.service.HomePageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/homepage")
public class HomePageController {
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

    @PostMapping("/edit")
    public ResponseEntity<Object> edit(@RequestBody HomeUpdateRequest requests) throws GeneralException {
        return ResponseEntity.ok(homePageService.updateHomePage(requests));
    }

    @PostMapping("/add-contact")
    public ResponseEntity<Object> getALL(@RequestBody ConcatCustomerRequest customerRequest) {
        return ResponseEntity.ok(contactCustomerService.add(customerRequest));
    }

    @PostMapping("/update-logo")
    public ResponseEntity<Object> getALL(@RequestBody List<PartnerRequest> partnerRequests) {
        return ResponseEntity.ok(homePageService.updateLogo(partnerRequests));
    }

    @PostMapping("/delete-logo")
    public ResponseEntity<Object> delete(@RequestParam Long id) {
        homePageService.deleteLogo(id);
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), "delete success", null));
    }

}
