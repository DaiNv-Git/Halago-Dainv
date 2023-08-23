package com.example.halagodainv.controller.viewdisplay;

import com.example.halagodainv.service.ViewNewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/view-news")
public class ViewNewController {
    private final ViewNewsService viewNewsService;

}
