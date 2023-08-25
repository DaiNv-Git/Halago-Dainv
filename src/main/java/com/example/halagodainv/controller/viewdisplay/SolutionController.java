package com.example.halagodainv.controller.viewdisplay;

import com.example.halagodainv.request.solution.livestream.SolutionLiveStreamImageEdit;
import com.example.halagodainv.request.solution.livestream.SolutionLiveStreamEdit;
import com.example.halagodainv.request.solution.review.SolutionReviewEditImage;
import com.example.halagodainv.request.solution.review.SolutionReviewRequestEdit;
import com.example.halagodainv.service.SolutionLiveStreamService;
import com.example.halagodainv.service.SolutionReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solution")
public class SolutionController {
    @Autowired
    private SolutionLiveStreamService solutionLiveStreamService;
    @Autowired
    private SolutionReviewService solutionReviewService;

    @PostMapping("/live-stream")
    public ResponseEntity<Object> getLiveStream(@RequestParam("language") String language) {
        return ResponseEntity.ok(solutionLiveStreamService.getSolution(language));
    }

    @PostMapping("/live-stream/detail")
    public ResponseEntity<Object> getLiveStreamDetail() {
        return ResponseEntity.ok(solutionLiveStreamService.getSolutionDetail());
    }

    @PostMapping("/live-stream/update")
    public ResponseEntity<Object> update(@RequestBody SolutionLiveStreamEdit solutionLiveStreamEdit) {
        return ResponseEntity.ok(solutionLiveStreamService.update(solutionLiveStreamEdit));
    }

//    @PostMapping("/live-stream/add-image")
//    public ResponseEntity<Object> addImage(@RequestBody List<SolutionLiveStreamImageEdit> imageRequests) {
//        return ResponseEntity.ok(solutionLiveStreamService.addImage(imageRequests));
//    }

    @PostMapping("/live-stream/delete-image")
    public ResponseEntity<Object> deleteImage(@RequestParam("imageId") long imageId) {
        solutionLiveStreamService.deleteImage(imageId);
        return ResponseEntity.status(HttpStatus.OK.value()).body("delete success");
    }

    @PostMapping("/review")
    public ResponseEntity<Object> getView(@RequestParam("language") String language) {
        return ResponseEntity.ok(solutionReviewService.getAll(language));
    }

    @PostMapping("/review/detail")
    public ResponseEntity<Object> getDetail() {
        return ResponseEntity.ok(solutionReviewService.getDetail());
    }

    @PostMapping("/review/update")
    public ResponseEntity<Object> delete(@RequestBody SolutionReviewRequestEdit solutionReviewRequestEdit) {
        return ResponseEntity.ok(solutionReviewService.updateContent(solutionReviewRequestEdit));
    }

    @PostMapping("/review/update-image")
    public ResponseEntity<Object> update(@RequestBody List<SolutionReviewEditImage> solutionLiveStreamEdit) {
        return ResponseEntity.ok(solutionReviewService.updateImageReview(solutionLiveStreamEdit));
    }
}
