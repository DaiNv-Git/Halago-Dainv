package com.example.halagodainv.request.news;

import com.example.halagodainv.request.SearchPageForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsFormSearch extends SearchPageForm {
    private String title = "";
    private Long topicId;
    private Integer tagId;
    private Boolean isHot;
}
