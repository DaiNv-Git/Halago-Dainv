package com.example.halagodainv.dto;

import com.example.halagodainv.model.ImageProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageProductDto {
    private int imageProductId;
    private int campaignId;
    private String imageProduct;

    public ImageProductDto(ImageProductEntity imageProductEntity) {
        this.imageProductId = imageProductEntity.getId();
        this.imageProduct = imageProductEntity.getImageProduct();
        this.campaignId = imageProductEntity.getCampaignEntity().getId();
    }

}
