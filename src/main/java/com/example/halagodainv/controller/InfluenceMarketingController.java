package com.example.halagodainv.controller;

import com.example.halagodainv.exception.ResourceNotFoundException;
import com.example.halagodainv.model.InfluencerMarketing;
import com.example.halagodainv.repository.InluenceMarketingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/influence-marketing")
public class InfluenceMarketingController {
    @Autowired
    private InluenceMarketingRepository inluenceMarketingRepository;

    @PutMapping("/update")
    public ResponseEntity<?> updateInfluenceMarketingList(@RequestBody List<InfluencerMarketing> updatedInfluencerMarketingList) {
        try {
            List<InfluencerMarketing> savedInfluencerMarketingList = new ArrayList<>();

            for (InfluencerMarketing updatedInfluencerMarketing : updatedInfluencerMarketingList) {
                // Check if ID is present for update, else create a new entry
                if (updatedInfluencerMarketing.getId() != null) {
                    InfluencerMarketing existingInfluencerMarketing = inluenceMarketingRepository.findById(updatedInfluencerMarketing.getId())
                            .orElseThrow(() -> new RuntimeException("InfluenceMarketing not found with id " + updatedInfluencerMarketing.getId()));

                    existingInfluencerMarketing.setLinkYoutobe(updatedInfluencerMarketing.getLinkYoutobe());
                    existingInfluencerMarketing.setOrders(updatedInfluencerMarketing.getOrders());

                    savedInfluencerMarketingList.add(inluenceMarketingRepository.save(existingInfluencerMarketing));
                } else {
                    savedInfluencerMarketingList.add(inluenceMarketingRepository.save(updatedInfluencerMarketing));
                }
            }

            return new ResponseEntity<>(savedInfluencerMarketingList, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update InfluenceMarketing list", HttpStatus.INTERNAL_SERVER_ERROR);
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
