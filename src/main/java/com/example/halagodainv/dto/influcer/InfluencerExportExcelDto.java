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
    private String link;
    private String follower;
    private String expense;
    private String address;
    private String industry;
    private String classify;
}
