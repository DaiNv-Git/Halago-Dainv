package com.example.halagodainv.controller;

import com.example.halagodainv.config.userconfig.UserAuthenConfig;
import com.example.halagodainv.exception.GeneralException;
import com.example.halagodainv.request.brand.BrandAddRequest;
import com.example.halagodainv.request.brand.BrandEditRequest;
import com.example.halagodainv.request.brand.BrandSearch;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brand")
public class BrandController {
    Logger logger = LoggerFactory.getLogger(BrandController.class);

    private final BrandService brandService;

    private final UserAuthenConfig userAuthenConfig;

    @PostMapping("")
    public ResponseEntity<Object> getByBrands(@RequestBody BrandSearch brandSearch) throws ParseException {
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), "success", brandService.getByListBrand(brandSearch.getPageNo(), brandSearch.getPageSize(), brandSearch.getBrandName(), brandSearch.getStartDate(), brandSearch.getEndDate())));
    }

    @PostMapping("/detail")
    public ResponseEntity<Object> getByBrands(@RequestParam(value = "branId") int branId) throws ParseException {
        String email = userAuthenConfig.getUser();
        return ResponseEntity.ok(brandService.getByDetail(branId, email));
    }

    @PostMapping("/add-brand")
    public ResponseEntity<Object> add(@Valid @RequestBody BrandAddRequest brandAddRequest) throws GeneralException {
        String email = userAuthenConfig.getUser();
        return ResponseEntity.ok(brandService.add(brandAddRequest, email));
    }

    @PostMapping("/edit-brand")
    public ResponseEntity<Object> add(@Valid @RequestBody BrandEditRequest brandEditRequest) throws GeneralException {
        String email = userAuthenConfig.getUser();
        return ResponseEntity.ok(brandService.edit(brandEditRequest, email));
    }

    @PostMapping("/delete-brand")
    public ResponseEntity<Object> add(@RequestParam("brandId") int brandId) throws GeneralException {
        return ResponseEntity.ok(brandService.deleteByBranId(brandId));
    }

}

