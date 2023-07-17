package com.example.halagodainv.controller;
import com.example.halagodainv.config.Constant;
import com.example.halagodainv.repository.ClassifyRepository;
import com.example.halagodainv.repository.IndustryRepository;
import com.example.halagodainv.request.Influencer.InfluencerAddRequest;
import com.example.halagodainv.request.news.NewsAddRequest;
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
    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findInfluencerById(@PathVariable("id")Integer id) {
        try {
            InfluencerResponse Influencer = influencerService.findInfluencerById(id);
            responseData = new BaseResponse(Constant.SUCCESS, "Tìm kiếm thành công", new BaseResponse(1,"", Influencer));
        } catch (Exception e) {
            logger.info(e.getMessage(), e.getCause());
            responseData = new BaseResponse(Constant.FAILED, "Tìm kiếm thât bại", new BaseResponse(0,"", null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }
    @PostMapping("/add")
    public ResponseEntity<Object> add(@Valid @RequestBody InfluencerAddRequest request) {
        return ResponseEntity.ok(influencerService.add(request));
    }
    @GetMapping("/getClassìy")
    public ResponseEntity<Object> getClassìy() {
        return ResponseEntity.ok(classifyRepository.findAll());
    }
    @GetMapping("/getField")
    public ResponseEntity<Object> getField() {
        return ResponseEntity.ok(industryRepository.findAll());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(influencerService.delete(id));
    }
}
