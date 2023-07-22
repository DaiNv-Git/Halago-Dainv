package com.example.halagodainv.controller;

import com.example.halagodainv.config.Constant;
import com.example.halagodainv.repository.BankRepository;
import com.example.halagodainv.repository.CityRepository;
import com.example.halagodainv.repository.ClassifyRepository;
import com.example.halagodainv.repository.IndustryRepository;
import com.example.halagodainv.request.excel.InfluceRequestExportExcel;
import com.example.halagodainv.request.influencer.InFluencerSubMenuSearch;
import com.example.halagodainv.request.influencer.InfluencerAddRequest;
import com.example.halagodainv.request.influencer.InfluencerSearch;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.response.Influencer.InfluencerResponse;
import com.example.halagodainv.service.InfluencerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/influencer")
public class InfluencerController {
    BaseResponse responseData = new BaseResponse(1, "Chưa có dữ liệu", null);
    Logger logger = LoggerFactory.getLogger(InfluencerController.class);
    @Autowired
    InfluencerService influencerService;
    @Autowired
    ClassifyRepository classifyRepository;
    @Autowired
    IndustryRepository industryRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private BankRepository bankRepository;

    @PostMapping(value = "/getMenuInflu")
    public ResponseEntity<Object> getMenul(@RequestBody InfluencerSearch search) {
        return ResponseEntity.status(HttpStatus.OK).body(influencerService.getInfluMenu(search));
    }

    @PostMapping(value = "/getSubMenuInflu")
    public ResponseEntity<Object> getSubMenu(@RequestBody InfluencerSearch search) {
        return ResponseEntity.status(HttpStatus.OK).body(influencerService.getInfluSubMenu(search));
    }

    @PostMapping(value = "/findById")
    public ResponseEntity<?> findInfluencerById(@RequestParam("id") long id) {
        return ResponseEntity.ok(influencerService.findInfluencerById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@Valid @RequestBody InfluencerAddRequest request) {
        return ResponseEntity.ok(influencerService.add(request));
    }

    @PostMapping("/update")
    public ResponseEntity<Object> update(@Valid @RequestBody InfluencerAddRequest request) {
        return ResponseEntity.ok(influencerService.edit(request));
    }

    @GetMapping("/getClassify")
    public ResponseEntity<Object> getClassify() {
        return ResponseEntity.ok(classifyRepository.findAll());
    }

    @GetMapping("/getIndustry")
    public ResponseEntity<Object> getField() {
        return ResponseEntity.ok(industryRepository.findAll());
    }

    @GetMapping("/getProvince")
    public ResponseEntity<Object> getProvince() {
        return ResponseEntity.ok(cityRepository.findAll());
    }

    @GetMapping("/getBank")
    public ResponseEntity<Object> getBank() {
        return ResponseEntity.ok(bankRepository.findAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id) {
        return ResponseEntity.ok(influencerService.delete(id));
    }

    @PostMapping("/export-excel")
    public ResponseEntity<Object> exportExcel(@RequestBody InfluceRequestExportExcel search) {
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), "success", influencerService.exportExcel(search)));
    }
}
