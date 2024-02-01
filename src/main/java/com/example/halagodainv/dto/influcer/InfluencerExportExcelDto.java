package com.example.halagodainv.dto.influcer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfluencerExportExcelDto {
    private Number id;
    private String name;
    private String sex;
    private String dateOfBirth;
    private String followerFacebook;
    private String expenseFacebook;
    private String followerTiktok;
    private String expenseTiktok;
    private String followerInstagram ;
    private String expenseInstagram ;
    private String followerYoutube ;
    private String expenseYoutube ;
    private String address;
    private String industry;
    private String classify;
    private String phone;
}
