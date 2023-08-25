package com.example.halagodainv.controller.viewdisplay;

import com.example.halagodainv.dto.story.StoryDetailDto;
import com.example.halagodainv.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/story")
public class StoryController {
    @Autowired
    private StoryService storyService;

    @PostMapping("")
    public ResponseEntity<Object> getHalago(@RequestParam("language") String language) {
        return ResponseEntity.ok(storyService.getStoryHalago(language));
    }

    @PostMapping("/detail")
    public ResponseEntity<Object> getDetail() {
        return ResponseEntity.ok(storyService.detailHalago());
    }

    @PostMapping("/update")
    public ResponseEntity<Object> update(@RequestBody StoryDetailDto request) {
        return ResponseEntity.ok(storyService.update(request));
    }
}
