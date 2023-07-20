package com.example.halagodainv.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "influencer_detail")
public class InfluencerDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "influId")
    private long influId;
    @Column(name = "follower")
    private String follower;
    @Column(name = "expense")
    private String expense;
    @Column(name = "url")
    private String url;
    @Column(name = "channel")
    private String channel;
}
