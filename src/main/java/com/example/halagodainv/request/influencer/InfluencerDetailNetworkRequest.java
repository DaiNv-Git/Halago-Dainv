package com.example.halagodainv.request.influencer;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfluencerDetailNetworkRequest {
    private String link;
    private Double follower;
    private Double expense;
    private String channel;
}
