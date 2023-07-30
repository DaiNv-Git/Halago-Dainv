package com.example.halagodainv.request.group;

import com.example.halagodainv.request.SearchPageForm;
import lombok.Data;

@Data
public class GroupSearch extends SearchPageForm {
    private String groupName = "";
    private String expense = "";
    private String memberTotal = "";
    private String industry = "";
}
