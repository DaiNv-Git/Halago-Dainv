package com.example.halagodainv.request.page;

import com.example.halagodainv.request.SearchPageForm;
import lombok.Data;

@Data
public class PageSearch extends SearchPageForm {
    private String pageName="";
    private String follower="";
    private String expense="";
    private String industry="";
}
