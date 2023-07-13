package com.example.halagodainv.request.campaign.imageproduct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageProductEditRequest {
    private int imageProductId;
    private String imageProduct;
}
