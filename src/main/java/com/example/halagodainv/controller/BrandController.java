package com.example.halagodainv.controller;

import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brand")
public class BrandController {
    Logger logger = LoggerFactory.getLogger(BrandController.class);

    private final BrandService brandService;

    @PostMapping("")
    public ResponseEntity<Object> getByBrands(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                              @RequestParam(value = "brandName", required = false) String brandName,
                                              @RequestParam(value = "status", required = false) Integer status) {
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), "success", brandService.getByListBrand(pageNo, pageSize, brandName, status)));
    }

    @PostMapping("/add-brand")
    public ResponseEntity<Object> add() {
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), "success", null));
    }

}

