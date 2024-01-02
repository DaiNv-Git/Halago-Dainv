package com.example.halagodainv.dto.viewnews;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewNewsDto {
    private int id;
    private String img;
    private String title;
    private String createdDate;
}
