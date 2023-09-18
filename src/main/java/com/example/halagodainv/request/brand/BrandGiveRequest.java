package com.example.halagodainv.request.brand;

import lombok.Data;

import javax.persistence.Column;
@Data
public class BrandGiveRequest {
    private String logoBrand;
    private String authorAvatar;
    private String authorName;
    private String position;
    private String content;
    private String contentEN;
}
