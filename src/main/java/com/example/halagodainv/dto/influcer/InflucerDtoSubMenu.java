package com.example.halagodainv.dto.influcer;

import com.example.halagodainv.service.impl.InfluencerServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    private List<Integer> industry = new ArrayList<>();

    public InflucerDtoSubMenu(long id, String name, String phone, String link, String follower, String expense, String industry) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.link = link;
        this.follower = follower;
        this.expense = expense;
        this.industry = InfluencerServiceImpl.parseStringToListOfIntegers(industry);
    }
}
