package com.example.halagodainv.dto.influcer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class InflucerMenuDto {
    private long id;
    private String name;
    private boolean isFacebook;
    private boolean isTikTok;
    private boolean isInstagram;
    private boolean isYouTube;
    private String industryId;
    private String industry;
    private String phone;

}
