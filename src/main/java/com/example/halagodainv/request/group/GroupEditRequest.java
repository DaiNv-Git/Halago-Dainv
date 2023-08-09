package com.example.halagodainv.request.group;

import lombok.Data;

import java.util.List;

@Data
public class GroupEditRequest {
    private long id;
    private String groupName;
    private long phone;
    private String link;
    private long memberTotal;
    private String expense;
    private List<Integer> industryId;
}
