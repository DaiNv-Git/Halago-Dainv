package com.example.halagodainv.controller.viewdisplay;

import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.service.ViewNewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/view-news")
public class ViewNewController {
    private final ViewNewsService viewNewsService;

    @PostMapping("")
    public ResponseEntity<?> viewNews(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                      @RequestParam(value = "language") String language) {
        try {
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), "success", viewNewsService.getViewNews(pageNo, pageSize, language)));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/detail")
    public ResponseEntity<?> viewNews(@RequestParam(value = "viewId") int viewId,
                                      @RequestParam(value = "language") String language) {
        try {
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), "success", viewNewsService.getViewNewsDetail(viewId, language)));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
