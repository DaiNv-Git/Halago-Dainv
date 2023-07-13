package com.example.halagodainv.dto.brand;

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
    private String registerName;
    private String email;
    private String phoneNumber;
    private String createDate;
    private String passwordHide;
    private String website;
    private String description;

    public BrandDto(BrandEntity brandEntity,String passwordHide) {
        this.id = brandEntity.getId();
        this.brandName = brandEntity.getBrandName();
        this.logo = brandEntity.getLogo();
        this.registerName = brandEntity.getRepresentativeName();
        this.email = brandEntity.getBrandEmail();
        this.phoneNumber = brandEntity.getBrandPhone();
        this.passwordHide = passwordHide;
        this.website = brandEntity.getWebsite();
        this.createDate = DateUtilFormat.convertDateToString(brandEntity.getCreated(), "dd-MM-yyyy");
        this.description = brandEntity.getDescription();
    }
    public BrandDto(BrandEntity brandEntity) {
        this.id = brandEntity.getId();
        this.brandName = brandEntity.getBrandName();
        this.logo = brandEntity.getLogo();
        this.registerName = brandEntity.getRepresentativeName();
        this.email = brandEntity.getBrandEmail();
        this.phoneNumber = brandEntity.getBrandPhone();
        this.website = brandEntity.getWebsite();
        this.createDate = DateUtilFormat.convertDateToString(brandEntity.getCreated(), "dd-MM-yyyy");
        this.description = brandEntity.getDescription();
    }
}
