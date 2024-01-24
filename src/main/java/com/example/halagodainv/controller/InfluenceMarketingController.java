package com.example.halagodainv.controller;

import com.example.halagodainv.exception.ResourceNotFoundException;
import com.example.halagodainv.model.InfluenceMarketing;
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
    @PostMapping("/create")
    public ResponseEntity<?> createInfluenceMarketing(@RequestBody InfluenceMarketing influenceMarketing) {
        try {
            InfluenceMarketing createdInfluenceMarketing = inluenceMarketingRepository.save(influenceMarketing);
            return new ResponseEntity<>(createdInfluenceMarketing, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create InfluenceMarketing", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInfluenceMarketing(@PathVariable int id, @RequestBody InfluenceMarketing updatedInfluenceMarketing) {
        try {
            InfluenceMarketing existingInfluenceMarketing = inluenceMarketingRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("InfluenceMarketing not found with id " + id));
            existingInfluenceMarketing.setLinkYoutobe(updatedInfluenceMarketing.getLinkYoutobe());
            existingInfluenceMarketing.setOrder(updatedInfluenceMarketing.getOrder());
            InfluenceMarketing savedInfluenceMarketing = inluenceMarketingRepository.save(existingInfluenceMarketing);
            return new ResponseEntity<>(savedInfluenceMarketing, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update InfluenceMarketing", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/Marketing/{id}")
    public ResponseEntity<?> getInfluenceMarketingDetail(@PathVariable int id) {
        try {
            InfluenceMarketing influenceMarketing = inluenceMarketingRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("InfluenceMarketing not found with id " + id));
            return new ResponseEntity<>(influenceMarketing, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to retrieve InfluenceMarketing details", HttpStatus.INTERNAL_SERVER_ERROR);
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
}
