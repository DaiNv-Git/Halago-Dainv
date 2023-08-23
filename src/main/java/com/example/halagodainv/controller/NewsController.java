package com.example.halagodainv.controller;

import com.example.halagodainv.request.news.NewsAddRequest;
import com.example.halagodainv.request.news.NewsFormSearch;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/news")
public class NewsController {
    Logger logger = LoggerFactory.getLogger(NewsController.class);
    @Autowired
    private NewsService newsService;

    @PostMapping("")
    public ResponseEntity<Object> get(@RequestBody NewsFormSearch newsSearch) {
        return ResponseEntity.ok(newsService.getNews(newsSearch));
    }

    @PostMapping("/detail")
    public ResponseEntity<Object> getDetail(@RequestParam("newId") int newId) {
        return ResponseEntity.ok(newsService.getDetail(newId));
    }

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

    @PostMapping("/view")
    public ResponseEntity<?> viewNews(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                      @RequestParam(value = "topicId") Long topicId,
                                      @RequestParam(value = "tagId") Long tagId,
                                      @RequestParam(value = "language") String language) {
        try {
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), "success", newsService.getViewNews(pageNo, pageSize, language, topicId, tagId)));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/view-detail")
    public ResponseEntity<?> viewNews(@RequestParam(value = "viewId") int viewId,
                                      @RequestParam(value = "topicId") Long topicId,
                                      @RequestParam(value = "tagId") Long tagId,
                                      @RequestParam(value = "language") String language) {
        try {
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), "success", newsService.getViewNewsDetail(viewId, language, topicId, tagId)));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/view/hot-new")
    public ResponseEntity<?> getNewsAndHot(@RequestParam(value = "language") String language) {
        try {
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), "success", newsService.getViewNewsAndHots(language)));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/view/topic")
    public ResponseEntity<?> getTopics(@RequestParam(value = "language") String language) {
        try {
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), "success", newsService.getTopic(language)));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/view/tags")
    public ResponseEntity<?> getTags() {
        try {
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), "success", newsService.getTag()));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/view/set-hot")
    public ResponseEntity<?> setViewHot(@RequestParam(value = "idNew") int idNew) {
        try {
            newsService.setIsHot(idNew);
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), "set success", null));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/view/set-not-hot")
    public ResponseEntity<?> setNotViewHot(@RequestParam(value = "idNew") int idNew) {
        try {
            newsService.setIsNotHot(idNew);
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), "set success", null));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
