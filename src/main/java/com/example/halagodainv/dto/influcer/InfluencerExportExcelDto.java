package com.example.halagodainv.dto.influcer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfluencerExportExcelDto {
    private long id;
    private String name;
    private String sex;
    private String dateOfBirth;
    private Boolean faceBook = false;
    private Boolean instagram = false;
    private Boolean tikTok = false;
    private Boolean youtube = false;
    private String address;
    private String industry;
    private String classify;
    private String phone;
}
