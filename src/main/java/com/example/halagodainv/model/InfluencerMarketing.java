package com.example.halagodainv.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "influence_marketing")
public class InfluencerMarketing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "link_youtobe")
    private String linkYoutobe;
    @Column(name = "order")
    private int order;
}
