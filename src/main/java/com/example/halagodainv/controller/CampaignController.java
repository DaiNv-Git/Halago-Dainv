package com.example.halagodainv.controller;

import com.example.halagodainv.request.campaign.CampaignAddRequest;
import com.example.halagodainv.request.campaign.CampaignEditRequest;
import com.example.halagodainv.request.campaign.CampaignSearch;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.service.CampaignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/campaign")
public class CampaignController {
    Logger logger = LoggerFactory.getLogger(CampaignController.class);
    @Autowired
    private CampaignService campaignService;

    @PostMapping("")
    public ResponseEntity<Object> getAll(@RequestBody CampaignSearch campaignSearch) {
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), "success", campaignService.getCampaigns(campaignSearch)));
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody CampaignAddRequest addRequest) throws ParseException {
        return ResponseEntity.ok(campaignService.add(addRequest));
    }

    @PostMapping("/edit")
    public ResponseEntity<Object> edit(@RequestBody CampaignEditRequest editRequest) throws ParseException {
        return ResponseEntity.ok(campaignService.edit(editRequest));
    }

    @PostMapping("/delete")
    public ResponseEntity<Object> edit(@RequestParam("campaignId") int campaignId) {
        return ResponseEntity.ok(campaignService.deleteByCampaign(campaignId));
    }
}
