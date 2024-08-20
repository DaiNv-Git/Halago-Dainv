package com.example.halagodainv.dto.campain;

import com.example.halagodainv.model.IndustryEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ConvertData {

    public static IndustryDto from(IndustryEntity data, String language) {
        if (language.equalsIgnoreCase("vn")) {
            return IndustryDto.builder().id(data.getId()).industryName(data.getIndustryName()).build();
        }
        return IndustryDto.builder().id(data.getId()).industryName(data.getIndustryNameEn()).build();
    }
}
