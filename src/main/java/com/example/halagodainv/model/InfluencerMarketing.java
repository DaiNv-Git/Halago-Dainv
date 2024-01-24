package com.example.halagodainv.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "influencer_marketing")
@Entity
public class InfluencerMarketing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String linkYoutobe;
    private String order;

}
