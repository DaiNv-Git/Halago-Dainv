package com.example.halagodainv.dto.campain;

import com.example.halagodainv.model.ImageProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageProductDto {
    private String imageProduct;

    public ImageProductDto(ImageProductEntity imageProductEntity) {
        this.imageProduct = imageProductEntity.getImageProduct();
    }

}
