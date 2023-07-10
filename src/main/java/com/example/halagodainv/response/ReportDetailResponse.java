package com.example.halagodainv.response;

import java.util.List;

public class ReportDetailResponse {

    private String department;

    private Integer student=0;
    private Integer restEmp=0;
    //tổng số lao động
    private Integer totalEmp=0;
    // lao động năng suất
    private Integer laborProductivityTeam=0;
    private Double ratio=0.0;
    //thoi vu to may
    private Integer partTimeEmp=0;
    private Integer riceVip=0;
    private Integer riceEmp=0;
    private Integer riceCus=0;

    List<ReportDetailResponse> child;
}
