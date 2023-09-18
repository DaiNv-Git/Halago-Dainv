package com.example.halagodainv.controller.viewdisplay;

import com.example.halagodainv.model.viewdisplayentity.BrandGiveEntity;
import com.example.halagodainv.request.brand.BrandGiveRequest;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.service.BrandGiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand-give")
public class BrandGiveController {

    @Autowired
    private BrandGiveService brandGiveService;
    @PostMapping("")
    public ResponseEntity<Object> getAll(@RequestParam("language") String language) {
        try {
            return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), "success", brandGiveService.getAll(language)));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @GetMapping("/detail")
    public ResponseEntity<Object> getDetail() {
        try {
            return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), "success", brandGiveService.getDetail()));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @PostMapping("/update")
    public ResponseEntity<Object> update(@RequestBody List<BrandGiveRequest> request) {
        try {
            return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), "success", brandGiveService.update(request)));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
