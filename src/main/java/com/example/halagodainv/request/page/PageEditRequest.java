package com.example.halagodainv.request.page;

import lombok.Data;

import java.util.List;

@Data
public class PageEditRequest {
    private long id;
    private String pageName;
    private String phone;
    private String link;
    private String follower;
    private String expense;
    private List<Integer> industryId;
}
