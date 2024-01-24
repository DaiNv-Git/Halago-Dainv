package com.example.halagodainv.service;

import com.example.halagodainv.model.viewdisplayentity.managekol.ManageKolEntity;
import com.example.halagodainv.response.BaseResponse;

public interface ManageKolService {
    BaseResponse<ManageKolEntity> getManageKol();
    BaseResponse<ManageKolEntity> update(ManageKolEntity manageKolEntity);
}
