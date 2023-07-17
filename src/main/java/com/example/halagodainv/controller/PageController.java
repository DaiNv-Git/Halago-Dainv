package com.example.halagodainv.controller;

import com.example.halagodainv.request.page.PageAddRequest;
import com.example.halagodainv.request.page.PageEditRequest;
import com.example.halagodainv.request.page.PageSearch;
import com.example.halagodainv.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/page")
public class PageController {
    @Autowired
    private PageService pageService;

    @PostMapping("")
    public ResponseEntity<Object> getAll(@RequestBody PageSearch pageSearch) {
        return ResponseEntity.ok(pageService.getPageAll(pageSearch));
    }

    @PostMapping("/detail")
    public ResponseEntity<Object> getDetail(@RequestParam("pageId") long pageId) {
        return ResponseEntity.ok(pageService.detail(pageId));
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody PageAddRequest pageAddRequest) {
        return ResponseEntity.ok(pageService.add(pageAddRequest));
    }

    @PostMapping("/edit")
    public ResponseEntity<Object> update(@RequestBody PageEditRequest pageEditRequest) {
        return ResponseEntity.ok(pageService.edit(pageEditRequest));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> delete(@RequestParam("pageId") long pageId) {
        return ResponseEntity.ok(pageService.delete(pageId));
    }
}
