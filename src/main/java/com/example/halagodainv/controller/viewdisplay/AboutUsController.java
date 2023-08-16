package com.example.halagodainv.controller.viewdisplay;

import com.example.halagodainv.request.about.AboutEditRequest;
import com.example.halagodainv.service.AboutUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/about")
public class AboutUsController {
    @Autowired
    private AboutUsService aboutUsService;

    @PostMapping("")
    public ResponseEntity<Object> getDetail() {
        return ResponseEntity.ok(aboutUsService.getDetail());
    }

    @PostMapping("/update")
    public ResponseEntity<Object> update(@RequestBody AboutEditRequest aboutEditRequest) {
        return ResponseEntity.ok(aboutUsService.update(aboutEditRequest));
    }
}
