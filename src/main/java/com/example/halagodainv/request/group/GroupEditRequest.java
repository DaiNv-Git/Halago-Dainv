package com.example.halagodainv.request.group;

import lombok.Data;

@Data
public class GroupEditRequest {
    private long id;
    private String groupName;
    private String phone;
    private String link;
    private String memberTotal;
    private String expense;
    private int industryId;
}
