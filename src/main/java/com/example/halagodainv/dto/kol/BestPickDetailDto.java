package com.example.halagodainv.dto.kol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class BestPickDetailDto {
    private long id;
    private String name;
    private String career;
    private String description;
    private Integer age;
    private String stageName;
}
