package com.example.halagodainv.controller.viewdisplay;

import com.example.halagodainv.request.brand.ViewBrandRequest;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.service.ViewBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/view-brand")
public class ViewBrandController {
    @Autowired
    private ViewBrandService brandService;

    @PostMapping("")
    public ResponseEntity<?> getViewBrands(@RequestParam("language") String language) {
        try {
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), "Success", brandService.getBranViews(language)));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/all")
    public ResponseEntity<?> getAll(@RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
                                    @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        try {
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), "Success", brandService.getAll(pageNo, pageSize)));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/detail")
    public ResponseEntity<?> getDetail(@RequestParam(value = "idView") Long idView) {
        try {
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), "Success", brandService.getDetail(idView)));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody ViewBrandRequest viewBrandRequest) {
        try {
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), "Success", brandService.add(viewBrandRequest)));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody ViewBrandRequest viewBrandRequest) {
        try {
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), "Success", brandService.edit(viewBrandRequest)));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam(value = "idView") Long idView) {
        try {
            brandService.delete(idView);
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), "Delete success", null));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
