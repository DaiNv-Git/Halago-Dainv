package com.example.halagodainv.request.campaign;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CampaignFormRelate {
   private List<Integer> industryIds = new ArrayList<>();
   private int campaignId;
   private int workStatus;
   private String language = "vn";
}
