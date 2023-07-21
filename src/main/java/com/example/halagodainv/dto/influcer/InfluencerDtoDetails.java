package com.example.halagodainv.dto.influcer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfluencerDtoDetails {
    private long id;
    private String name;
    private String phone;
    private String industry;
    private Boolean isSex;
    private String birtYear;
    private int classify ;
    private int provinceId;
    private String address;
    private int bankId;
    private String bankNumber;
    private String createHistory;
    private String linkFb;
    private String followerFb;
    private String expenseFb;
    private String linkTT;
    private String followerTT;
    private String expenseTT;
    private String linkYT;
    private String followerYT;
    private String expenseYT;
    private String linkIns;
    private String followerIns;
    private String expenseIns;
}