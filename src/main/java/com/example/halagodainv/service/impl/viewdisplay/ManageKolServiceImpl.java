package com.example.halagodainv.service.impl.viewdisplay;

import com.example.halagodainv.model.viewdisplayentity.managekol.ManageKolEntity;
import com.example.halagodainv.repository.viewdisplay.managekol.ManageKolRepository;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.service.ManageKolService;
import com.example.halagodainv.until.FileImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageKolServiceImpl implements ManageKolService {

    @Autowired
    private ManageKolRepository manageKolRepository; @Autowired
    private FileImageUtil fileImageUtil;

    public BaseResponse<ManageKolEntity> getManageKol() {
        return new BaseResponse<>(200, "success", manageKolRepository.findById(1).get());
    }

    public BaseResponse<ManageKolEntity> update(ManageKolEntity manageKolEntity) {
        if (manageKolRepository.findById(1).isPresent()){
            ManageKolEntity manageKol = manageKolRepository.findById(1).get();
            manageKol.setProfessionalManagement(fileImageUtil.uploadImage(manageKolEntity.getProfessionalManagement()));
            manageKol.setImg1(fileImageUtil.uploadImage(manageKolEntity.getImg1()));
            manageKol.setImg2(fileImageUtil.uploadImage(manageKolEntity.getImg2()));
            manageKol.setImg3(fileImageUtil.uploadImage(manageKolEntity.getImg3()));
            manageKol.setImg4(fileImageUtil.uploadImage(manageKolEntity.getImg4()));
            manageKolRepository.save(manageKol);
            return new BaseResponse<>(200, "success", manageKol);
        }
        return new BaseResponse<>(400, "not success", null);
    }
}
