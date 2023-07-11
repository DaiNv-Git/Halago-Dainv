package com.example.halagodainv.dto;

import com.example.halagodainv.model.BrandEntity;
import com.example.halagodainv.until.DateUtilFormat;
import lombok.*;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BrandDto {
    private int id;
    private String brandName;
    private String logo;
    private String registerName;
    private String brandEmail;
    private String phone;
    private String createDate;
    private String passwordHide;

    public BrandDto(BrandEntity brandEntity,String passwordHide) {
        this.id = brandEntity.getId();
        this.brandName = brandEntity.getBrandName();
        this.logo = brandEntity.getLogo();
        this.registerName = brandEntity.getRepresentativeName();
        this.brandEmail = brandEntity.getBrandEmail();
        this.phone = brandEntity.getBrandPhone();
        this.passwordHide = passwordHide;
        this.createDate = DateUtilFormat.convertDateToString(brandEntity.getCreated(), "dd-MM-yyyy");
    }
    public BrandDto(BrandEntity brandEntity) {
        this.id = brandEntity.getId();
        this.brandName = brandEntity.getBrandName();
        this.logo = brandEntity.getLogo();
        this.registerName = brandEntity.getRepresentativeName();
        this.brandEmail = brandEntity.getBrandEmail();
        this.phone = brandEntity.getBrandPhone();
        this.createDate = DateUtilFormat.convertDateToString(brandEntity.getCreated(), "dd-MM-yyyy");
    }
}
