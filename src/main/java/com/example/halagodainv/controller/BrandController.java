package com.example.halagodainv.controller;

import com.example.halagodainv.config.userconfig.UserAuthenConfig;
import com.example.halagodainv.exception.GeneralException;
import com.example.halagodainv.model.UserEntity;
import com.example.halagodainv.request.brand.BrandAddRequest;
import com.example.halagodainv.request.brand.BrandEditRequest;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brand")
public class BrandController {
    Logger logger = LoggerFactory.getLogger(BrandController.class);

    private final BrandService brandService;

    private final UserAuthenConfig userAuthenConfig;

    @PostMapping("")
    public ResponseEntity<Object> getByBrands(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                              @RequestParam(value = "brandName", required = false) String brandName,
                                              @RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
                                              @RequestParam(value = "endDate", required = false, defaultValue = "") String endDate) throws ParseException {
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), "success", brandService.getByListBrand(pageNo, pageSize, brandName, startDate, endDate)));
    }

    @PostMapping("/add-brand")
    public ResponseEntity<Object> add(@Valid @RequestBody BrandAddRequest brandAddRequest) throws GeneralException {
        String email = userAuthenConfig.getUser();
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), "save success", brandService.add(brandAddRequest, email)));
    }

    @PostMapping("/edit-brand")
    public ResponseEntity<Object> add(@Valid @RequestBody BrandEditRequest brandEditRequest) throws GeneralException {
        String email = userAuthenConfig.getUser();
        return ResponseEntity.ok(brandService.edit(brandEditRequest, email));
    }

}

