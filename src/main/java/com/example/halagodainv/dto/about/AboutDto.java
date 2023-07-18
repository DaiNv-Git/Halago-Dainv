package com.example.halagodainv.dto.about;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AboutDto {
    private int id;
    private String contentVN;
    private String contentEN;
    private String titleVN;
    private String titleEN;
    private String language;
}
