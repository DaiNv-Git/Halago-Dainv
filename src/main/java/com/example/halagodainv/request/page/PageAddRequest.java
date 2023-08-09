package com.example.halagodainv.request.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageAddRequest {
    private String pageName;
    private String phone;
    private String link;
    private String follower;
    private String expense;
    private List<Integer> industryId;
}
