package com.example.halagodainv.controller;

import com.example.halagodainv.service.IndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndustryController {
    @Autowired
    private IndustryService industryService;

    @GetMapping("/industry")
    public ResponseEntity<Object> getIndustry() {
        return ResponseEntity.ok(industryService.getByIndustry());
    }
}
