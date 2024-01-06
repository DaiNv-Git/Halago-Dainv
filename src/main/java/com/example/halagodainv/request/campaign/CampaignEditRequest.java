package com.example.halagodainv.request.campaign;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CampaignEditRequest {
    private int id;
    private String brandName;
    private String image;
    private String campaignName;
    private String campaignNameEN;
    private int workStatus;
    private List<Integer> industryId = new ArrayList<>();
    private List<Integer> campaignCommunication = new ArrayList<>();
    private List<Integer> campaignCategory = new ArrayList<>();
    private String thumbnail1;
    private String thumbnail2;
    private String thumbnail3;
    private String hashtag;
    private String conditionApply;
    private String method;
    private String outstandingProduct;
    private String content;
    private String other;
    private String conditionApplyEN;
    private String methodEN;
    private String outstandingProductEN;
    private String contentEN;
    private String otherEN;
    private String timeDeadline;
}
