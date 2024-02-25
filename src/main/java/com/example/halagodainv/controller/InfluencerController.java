package com.example.halagodainv.controller;

import com.example.halagodainv.config.userconfig.UserAuthenLogin;
import com.example.halagodainv.exception.GeneralException;
import com.example.halagodainv.repository.BankRepository;
import com.example.halagodainv.repository.CityRepository;
import com.example.halagodainv.repository.ClassifyRepository;
import com.example.halagodainv.repository.IndustryRepository;
import com.example.halagodainv.request.excel.InfluceRequestExportExcel;
import com.example.halagodainv.request.influencer.InfluencerAddRequest;
import com.example.halagodainv.request.influencer.InfluencerSearch;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.service.InfluencerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/influencer")
public class InfluencerController extends UserAuthenLogin {
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
        return ResponseEntity.status(HttpStatus.OK).body(influencerService.getAll(search));
    }

    @PostMapping(value = "/getSubMenuInflu")
    public ResponseEntity<Object> getSubMenu(@RequestBody InfluencerSearch search) {
        return ResponseEntity.status(HttpStatus.OK).body(influencerService.getSubInflu(search));
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
    public ResponseEntity<Object> exportExcel(@RequestBody InfluencerSearch search) {
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), "success", influencerService.exportExcel(search)));
    }

    @PostMapping("/import-excel")
    public ResponseEntity<Object> importExcel(@RequestParam MultipartFile file) throws GeneralException, IOException {
        try {
            influencerService.importExcel(file);
            return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), "success", null));
        } catch (IOException ex) {
            throw new GeneralException(ex.getLocalizedMessage());
        }
    }

    @PostMapping("/down-file-import-excel")
    public ResponseEntity<Object> downFileImportExcel() {
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), "success", influencerService.downFileImportExcel()));
    }

    @PostMapping("/isCheckAccount")
    public ResponseEntity<Object> IsCheckEmailAccount(@RequestParam("email") String email) {
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), "", influencerService.isCheckInforInflu(email)));
    }
}
