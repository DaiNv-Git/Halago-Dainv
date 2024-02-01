package com.example.halagodainv.dto.influcer;

import lombok.*;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InflucerMenuDto {
    private Number id;
    private String name;
    private boolean isFacebook;
    private boolean isTikTok;
    private boolean isInstagram;
    private boolean isYouTube;
    private String industryId;
    private String industry;
    private String phone;


}
