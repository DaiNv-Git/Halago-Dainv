package com.example.halagodainv.controller;

import com.example.halagodainv.repository.CareerRepository;
import com.example.halagodainv.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CareerController {
    @Autowired
    private CareerRepository careerRepository;

    @GetMapping("/career")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), "Lấy dữ liệu thành công", careerRepository.findAll()));
    }
}
