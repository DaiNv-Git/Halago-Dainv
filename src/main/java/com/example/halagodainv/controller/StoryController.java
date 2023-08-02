package com.example.halagodainv.controller;

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

    @PostMapping("/activities")
    public ResponseEntity<Object> getHalagoActivities(@RequestParam("language") String language) {
        return ResponseEntity.ok(storyService.getStoryHalagoActivities(language));
    }

    @PostMapping("/detail")
    public ResponseEntity<Object> getDetail() {
        return ResponseEntity.ok(storyService.detailHalago());
    }

    @PostMapping("/update")
    public ResponseEntity<Object> update(@RequestBody StoryDetailDto request) {
        return ResponseEntity.ok(storyService.update(request));
    }

    @PostMapping("/delete-halago")
    public ResponseEntity<Object> deleteHalato(@RequestParam("id") long id) {
        return ResponseEntity.ok(storyService.deleteHalago(id));
    }

    @PostMapping("/update-activities")
    public ResponseEntity<Object> update(@RequestParam("id") long id) {
        return ResponseEntity.ok(storyService.deleteHalagoActivities(id));
    }
}
