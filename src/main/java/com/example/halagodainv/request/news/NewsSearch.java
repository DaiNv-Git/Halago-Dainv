package com.example.halagodainv.request.news;

import com.example.halagodainv.request.SearchPage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsSearch extends SearchPage {
    private String title = "";
}
