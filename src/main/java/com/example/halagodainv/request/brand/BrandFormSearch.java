package com.example.halagodainv.request.brand;

import com.example.halagodainv.request.SearchPageForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandFormSearch extends SearchPageForm {
    private String brandName = "";
}
