package com.example.halagodainv.controller;

import com.example.halagodainv.exception.ResourceNotFoundException;
import com.example.halagodainv.model.InfluencerMarketing;
import com.example.halagodainv.repository.InluenceMarketingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/influence-marketing")
public class InfluenceMarketingController {
    @Autowired
    private InluenceMarketingRepository inluenceMarketingRepository;

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInfluenceMarketing(@PathVariable int id, @RequestBody InfluencerMarketing updatedInfluencerMarketing) {
        try {
            InfluencerMarketing existingInfluencerMarketing = inluenceMarketingRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("InfluenceMarketing not found with id " + id));
            existingInfluencerMarketing.setLinkYoutobe(updatedInfluencerMarketing.getLinkYoutobe());
            existingInfluencerMarketing.setOrder(updatedInfluencerMarketing.getOrder());
            InfluencerMarketing savedInfluencerMarketing = inluenceMarketingRepository.save(existingInfluencerMarketing);
            return new ResponseEntity<>(savedInfluencerMarketing, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update InfluenceMarketing", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(inluenceMarketingRepository.findAll(), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to retrieve InfluenceMarketing details", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/detail")
    public ResponseEntity<?> detail() {
        try {
            return new ResponseEntity<>(inluenceMarketingRepository.findAll(), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to retrieve InfluenceMarketing details", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
