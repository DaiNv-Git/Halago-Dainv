package com.example.halagodainv.controller.viewdisplay;

import com.example.halagodainv.model.viewdisplayentity.managekol.ManageKolEntity;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.service.ManageKolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManageKolController {
    @Autowired
    private ManageKolService manageKolService;

    @GetMapping("/manage-kol")
    public BaseResponse<ManageKolEntity> getManageKol(){
        return manageKolService.getManageKol();
    }

    @GetMapping("/manage-kol/detail")
    public BaseResponse<ManageKolEntity> getManageKolDetail(){
        return manageKolService.getManageKol();
    }

    @GetMapping("/manage-kol/update")
    public BaseResponse<ManageKolEntity> update(ManageKolEntity manageKolEntity) {
        return manageKolService.update(manageKolEntity);
    }
}
