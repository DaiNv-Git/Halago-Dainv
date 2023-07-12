package com.example.halagodainv.controller;
import com.example.halagodainv.exception.GeneralException;
import com.example.halagodainv.request.brand.BrandAddRequest;
import com.example.halagodainv.request.news.NewsAddRequest;
import com.example.halagodainv.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/news")
public class NewsController {
    Logger logger = LoggerFactory.getLogger(NewsController.class);
    @Autowired
    private  NewsService newsService;
    @PostMapping("/add")
    public ResponseEntity<Object> add(@Valid @RequestBody NewsAddRequest newsAddRequest) {
        return ResponseEntity.ok(newsService.insertNews(newsAddRequest));
    }
    @PutMapping("/update")
    public ResponseEntity<Object> update(@Valid @RequestBody NewsAddRequest newsAddRequest) {
        return ResponseEntity.ok(newsService.update(newsAddRequest));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(newsService.delete(id));
    }
}
