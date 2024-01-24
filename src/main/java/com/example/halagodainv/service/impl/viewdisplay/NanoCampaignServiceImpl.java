package com.example.halagodainv.service.impl.viewdisplay;

import com.example.halagodainv.model.viewdisplayentity.nanocampaign.NanoCampaignEntity;
import com.example.halagodainv.repository.viewdisplay.nanocampagin.NanoCampaignRepository;
import com.example.halagodainv.request.nanocampaign.NanoCampaignRequest;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.service.NanoCampaignService;
import com.example.halagodainv.until.FileImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class NanoCampaignServiceImpl implements NanoCampaignService {

    @Autowired
    private NanoCampaignRepository nanoCampaignRepository;

    @Autowired
    private FileImageUtil fileImageUtil;

    public BaseResponse<List<NanoCampaignEntity>> getList() {
        return new BaseResponse<>(200, "sucess", nanoCampaignRepository.findAllByOrderByNameFileDesc());
    }

    public BaseResponse<List<NanoCampaignEntity>> updateNano(List<NanoCampaignRequest> nanoCampaignRequests) {
        if (!CollectionUtils.isEmpty(nanoCampaignRequests)) {
            nanoCampaignRepository.deleteAll();
            List<NanoCampaignEntity> nanoCampaignEntities = new ArrayList<>();
            for (NanoCampaignRequest request : nanoCampaignRequests) {
                NanoCampaignEntity nanoCampaignEntity = new NanoCampaignEntity();
                nanoCampaignEntity.setNameFile(request.getNameFile());
                nanoCampaignEntity.setImg(fileImageUtil.uploadImage(request.getImg()));
                nanoCampaignEntities.add(nanoCampaignEntity);
            }
            nanoCampaignRepository.saveAll(nanoCampaignEntities);
            return new BaseResponse<>(200, "sucess", nanoCampaignRepository.findAllByOrderByNameFileDesc());
        }
        return new BaseResponse<>(200, "sucess", nanoCampaignRepository.findAllByOrderByNameFileDesc());
    }
}
