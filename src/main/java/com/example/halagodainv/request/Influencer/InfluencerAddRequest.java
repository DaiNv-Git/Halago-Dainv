package com.example.halagodainv.request.Influencer;
import lombok.Data;

import javax.persistence.Column;
import java.util.List;

@Data
public class InfluencerAddRequest {
    private String name;
    private String phoneNumber;
    private Integer sex;
    private String year;
    List<InfluencerSocialNetwordRequest> influencerRequestList;
    private String email;
    private List<Integer> type;
    private String field;

    private String classify ;
    private Integer province;
    private String address;
    private String bankName;
    private String bankNumber;
    @Column(name = "fb")
    private int facebook;
    @Column(name = "youtobe")
    private int youtobe;
    @Column(name = "titok")
    private int tiktok;
    @Column(name = "instagram")
    private int instagram;
}
