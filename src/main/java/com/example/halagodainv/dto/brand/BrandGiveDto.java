package com.example.halagodainv.dto.brand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandGiveDto {
    private String logoBrand;
    private String authorAvatar;
    private String authorName;
    private String position;
    private String content;
}
