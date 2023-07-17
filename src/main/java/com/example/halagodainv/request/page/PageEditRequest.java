package com.example.halagodainv.request.page;

import lombok.Data;

@Data
public class PageEditRequest {
    private long id;
    private String pageName;
    private String phone;
    private String link;
    private String follower;
    private String expense;
    private int industryId;
}
