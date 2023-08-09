package com.example.halagodainv.request.group;

import lombok.Data;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Data
public class GroupAddRequest {
    private String groupName;
    private long phone;
    private String link;
    private long memberTotal;
    private String expense;
    private List<Integer> industryId;
}
