package com.example.halagodainv.controller;

import com.example.halagodainv.exception.GeneralException;
import com.example.halagodainv.service.HomePageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/homepage")
public class HomePage {
    private final HomePageService homePageService;

    @PostMapping("")
    public ResponseEntity<Object> getAll(@RequestParam("language") String language) throws GeneralException {
        return ResponseEntity.ok(homePageService.getHomePage(language));
    }

    @PostMapping("/partner")
    public ResponseEntity<Object> getAll(@RequestParam(value = "partnerId",defaultValue = "1",required = false) int partnerId) throws GeneralException {
        return ResponseEntity.ok(homePageService.getPartner(partnerId));
    }

}
