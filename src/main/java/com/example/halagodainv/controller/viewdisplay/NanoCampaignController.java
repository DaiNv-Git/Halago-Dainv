package com.example.halagodainv.controller.viewdisplay;

import com.example.halagodainv.model.viewdisplayentity.nanocampaign.NanoCampaignEntity;
import com.example.halagodainv.request.nanocampaign.NanoCampaignRequest;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.service.NanoCampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NanoCampaignController {

    @Autowired
    private NanoCampaignService nanoCampaignService;

    @GetMapping("/nano-campaign")
    public BaseResponse<List<NanoCampaignEntity>> getAll() {
        return nanoCampaignService.getList();
    }

    @GetMapping("/nano-campaign/detail")
    public BaseResponse<List<NanoCampaignEntity>> getDetail() {
        return nanoCampaignService.getList();
    }

    @PostMapping("/nano-campaign/update")
    public BaseResponse<List<NanoCampaignEntity>> update(@RequestBody List<NanoCampaignRequest> requests) {
        return nanoCampaignService.updateNano(requests);
    }
}
