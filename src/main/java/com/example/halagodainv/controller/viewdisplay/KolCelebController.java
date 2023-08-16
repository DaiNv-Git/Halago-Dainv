package com.example.halagodainv.controller.viewdisplay;

import com.example.halagodainv.request.kolCeleb.KolCelebRequest;
import com.example.halagodainv.service.KolCelebService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kob-celeb")
public class KolCelebController {
    private final KolCelebService kolCelebService;

    @PostMapping("")
    public ResponseEntity<Object> getALL(@RequestParam("language") String language) {
        return ResponseEntity.ok(kolCelebService.getKolAll(language));
    }

    @PostMapping("/detail")
    public ResponseEntity<Object> getDetail() {
        return ResponseEntity.ok(kolCelebService.getDetail());
    }

    @PostMapping("/detail-celeb")
    public ResponseEntity<Object> getDetailCeleb(@RequestParam(value = "id", defaultValue = "0", required = false) long id, @RequestParam("language") String language) {
        return ResponseEntity.ok(kolCelebService.getDetailCeleb(id, language));
    }

    @PostMapping("/update")
    public ResponseEntity<Object> update(@RequestBody KolCelebRequest request) {
        return ResponseEntity.ok(kolCelebService.update(request));
    }
}
