package com.example.halagodainv.dto.influcer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.LifecycleState;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfluencerDtoDetails {
    private long id;
    private String name;
    private String phone;
    private List<Integer> industry = new ArrayList<>();
    private Integer sex;
    private String birtYear;
    private List<Integer> classifyId = new ArrayList<>();
    private Integer provinceId;
    private String address;
    private String email;
    private String bankId;
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
