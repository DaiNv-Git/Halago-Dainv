package com.example.halagodainv.request.about;

import lombok.Data;

@Data
public class AboutEditRequest {
    private int id;
    private String contentVN;
    private String contentEN;
    private String titleVN;
    private String titleEN;
}
