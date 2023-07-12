package com.example.halagodainv.request.brand;

import com.example.halagodainv.request.SearchPage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandSearch extends SearchPage {
    private String brandName = "";
}
