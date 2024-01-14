package com.example.halagodainv.dto.influcer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class InflucerDtoSubMenu {
    private long id;
    private String name;
    private String phone;
    private String link;
    private String follower;
    private String expense;
    private String industryId;
    private String industry;
}
