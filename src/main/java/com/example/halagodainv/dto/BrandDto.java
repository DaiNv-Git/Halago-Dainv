package com.example.halagodainv.dto;

import com.example.halagodainv.model.BrandEntity;
import com.example.halagodainv.until.DateUtilFormat;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BrandDto {
    private int id;
    private String brandName;
    private String logo;
    private String representativeName;
    private String brandEmail;
    private String phone;
    private String createDate;
    private int status;

    public BrandDto(BrandEntity brandEntity) {
        this.id = brandEntity.getId();
        this.brandName = brandEntity.getBrandName();
        this.logo = brandEntity.getLogo();
        this.representativeName = brandEntity.getRepresentativeName();
        this.brandEmail = brandEntity.getBrandEmail();
        this.phone = brandEntity.getBrandPhone();
        this.createDate = DateUtilFormat.convertDateToString(brandEntity.getCreated(), "dd-MM-yyyy");
        this.status = brandEntity.getStatus();

    }
}
