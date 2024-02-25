package com.example.halagodainv.controller;

import com.example.halagodainv.config.userconfig.UserAuthenLogin;
import com.example.halagodainv.exception.ErrorResponse;
import com.example.halagodainv.request.campaign.CampaignAddRequest;
import com.example.halagodainv.request.campaign.CampaignEditRequest;
import com.example.halagodainv.request.campaign.CampaignFormRelate;
import com.example.halagodainv.request.campaign.CampaignFormSearch;
import com.example.halagodainv.response.BaseResponse;

import com.example.halagodainv.service.CampaignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/campaign")
public class CampaignController extends UserAuthenLogin {
    Logger logger = LoggerFactory.getLogger(CampaignController.class);
    @Autowired
    private CampaignService campaignService;

    @PostMapping("")
    public ResponseEntity<Object> getAll(@RequestBody CampaignFormSearch campaignSearch) {
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), "success", campaignService.getCampaigns(campaignSearch)));
    }

    @PostMapping("/detail")
    public ResponseEntity<Object> getDetail(@RequestParam("campaignId") int campaignId,
                                            @RequestParam(value = "language", required = false, defaultValue = "vn") String language) {
        return ResponseEntity.ok(campaignService.getDetail(campaignId, language));
    }

    @PostMapping("/detail_full")
    public ResponseEntity<Object> getDetailFull(@RequestParam("campaignId") int campaignId) {
        return ResponseEntity.ok(campaignService.getDetailFull(campaignId));
    }

    @PostMapping("/relate-to-campaign")
    public ResponseEntity<Object> RelateToCampaigns(@RequestBody CampaignFormRelate campaignFormRelate) {
        return ResponseEntity.ok(campaignService.getRelateToCampaigns(campaignFormRelate.getIndustryIds(), campaignFormRelate.getCampaignId(), campaignFormRelate.getWorkStatus(), campaignFormRelate.getLanguage()));
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

    @GetMapping("/brand")
    public ResponseEntity<Object> getBrands() {
        return ResponseEntity.ok(campaignService.getByBrands());
    }

    @GetMapping("/industry")
    public ResponseEntity<Object> getIndustry() {
        return ResponseEntity.ok(campaignService.getByIndustry());
    }

    @GetMapping("/campaign-recruitment")
    public ResponseEntity<?> campaignRecruitment(@RequestParam("idCampaign") int idCampagin) {
        if (getUserLogin().isPresent() && getUserLogin().get().getRoleId() == 3) {
            return ResponseEntity.ok(campaignService.isCheckRecruitment(getUserLogin().get().getId(), idCampagin));
        }
        return ResponseEntity.ok(new ErrorResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Đây không phải là tài khoản influencer!", null));
    }

    @GetMapping("/campaign-recruitment-list")
    public ResponseEntity<Object> campaignRecruitmentList(@RequestParam("campaignId") int campaignId,
                                                          @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
                                                          @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
                                                          @RequestParam(value = "language", defaultValue = "vn", required = false) String language) {
        return ResponseEntity.ok(campaignService.getRecruitmentList(campaignId, pageSize, pageNo, language));
    }

    @GetMapping("/work-status")
    public ResponseEntity<Object> getCampaignStatus() {
        return ResponseEntity.ok(campaignService.getCampaignStatuses());
    }

    @GetMapping("/work-communication")
    public ResponseEntity<Object> getCampaignCommunication() {
        return ResponseEntity.ok(campaignService.getCampaignCommunications());
    }

    @GetMapping("/work-category")
    public ResponseEntity<Object> getCampaign() {
        return ResponseEntity.ok(campaignService.getCampaignCategories());
    }
    @GetMapping("/campaign-user-list")
    public ResponseEntity<Object> campaignRecruitmentListUser(@RequestParam("campaignId") int campaignId,
                                                              @RequestParam(value ="userName", required = false) String userName,
                                                              @RequestParam(value = "language", defaultValue = "vn", required = false) String language,
                                                          @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
                                                          @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return ResponseEntity.ok(campaignService.getRecruitmentUserList(campaignId,userName,language, pageSize, pageNo, pageable));
    }
    @DeleteMapping("/delete/influ")
    public ResponseEntity<?> delete(@RequestParam int campainId,@RequestParam int influId) {
        campaignService.deleteByInfluId(campainId,influId);
        return ResponseEntity.ok("OK");
    }
}
