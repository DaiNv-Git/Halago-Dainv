package com.example.halagodainv.controller;

import com.example.halagodainv.config.userconfig.UserAuthenConfig;
import com.example.halagodainv.exception.GeneralException;
import com.example.halagodainv.request.brand.BrandAddRequest;
import com.example.halagodainv.request.brand.BrandEditRequest;
import com.example.halagodainv.request.brand.BrandFormSearch;
import com.example.halagodainv.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brand")
public class BrandController {
    Logger logger = LoggerFactory.getLogger(BrandController.class);
    private final BrandService brandService;

    @PostMapping("")
    public ResponseEntity<Object> getByBrands(@RequestBody BrandFormSearch brandSearch) throws ParseException {
        return ResponseEntity.ok(brandService.getByListBrand(brandSearch.getPageNo(), brandSearch.getPageSize(), brandSearch.getBrandName(), brandSearch.getStartDate(), brandSearch.getEndDate()));
    }

    @PostMapping("/detail")
    public ResponseEntity<Object> getByBrands(@RequestParam(value = "branId") int branId) throws ParseException {
        return ResponseEntity.ok(brandService.getByDetail(branId));
    }

    @PostMapping("/add-brand")
    public ResponseEntity<Object> add(@Valid @RequestBody BrandAddRequest brandAddRequest) throws GeneralException {
        return ResponseEntity.ok(brandService.add(brandAddRequest, brandAddRequest.getEmail()));
    }

    @PostMapping("/edit-brand")
    public ResponseEntity<Object> add(@Valid @RequestBody BrandEditRequest brandEditRequest) throws GeneralException {
        return ResponseEntity.ok(brandService.edit(brandEditRequest, brandEditRequest.getEmail()));
    }

    @PostMapping("/delete-brand")
    public ResponseEntity<Object> add(@RequestParam("brandId") int brandId) throws GeneralException {
        return ResponseEntity.ok(brandService.deleteByBranId(brandId));
    }

    @GetMapping("/brand-all")
    public ResponseEntity<Object> getBrands() {
        return ResponseEntity.ok(brandService.getByBrands());
    }

}

