package com.example.halagodainv.controller;

import com.example.halagodainv.request.foot.FootEditRequest;
import com.example.halagodainv.service.FooterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/footer")
public class FooterController {
    @Autowired
    private FooterService footerService;

    @PostMapping("")
    public ResponseEntity<Object> getDetail() {
        return ResponseEntity.ok(footerService.getFoot());
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateFooter(@RequestBody FootEditRequest footEditRequest) {
        return ResponseEntity.ok(footerService.update(footEditRequest));
    }
}
