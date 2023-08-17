package com.example.halagodainv.controller.viewdisplay;

import com.example.halagodainv.request.news.ViewNewsRequest;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.service.ViewNewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/view-news")
public class ViewNewController {
    private final ViewNewsService viewNewsService;

    @PostMapping("")
    public ResponseEntity<?> viewNews(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                      @RequestParam(value = "topicId", defaultValue = "0") Long topicId,
                                      @RequestParam(value = "language") String language) {
        try {
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), "success", viewNewsService.getViewNews(pageNo, pageSize, language, topicId)));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/view-detail")
    public ResponseEntity<?> viewNews(@RequestParam(value = "viewId") int viewId,
                                      @RequestParam(value = "topicId", defaultValue = "0") Long topicId,
                                      @RequestParam(value = "language") String language) {
        try {
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), "success", viewNewsService.getViewNewsDetail(viewId, language, topicId)));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/detail")
    public ResponseEntity<?> detail(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                    @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        try {
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), "success", viewNewsService.getDetail(pageNo, pageSize)));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> detail(@RequestBody List<ViewNewsRequest> requests) {
        try {
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), "success", viewNewsService.updateNews(requests)));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
