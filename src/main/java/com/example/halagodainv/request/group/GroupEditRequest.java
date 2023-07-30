package com.example.halagodainv.request.group;

import lombok.Data;

import java.util.List;

@Data
public class GroupEditRequest {
    private long id;
    private String groupName;
    private String phone;
    private String link;
    private String memberTotal;
    private String expense;
    private List<Integer> industryId;
}
