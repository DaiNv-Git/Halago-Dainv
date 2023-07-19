package com.example.halagodainv.dto.influcer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InflucerDto {
    private long id;
    private String name;
    private boolean isFacebook;
    private boolean isTikTok;
    private boolean isInstagram;
    private boolean isYouTube;
    private String industry;
}
