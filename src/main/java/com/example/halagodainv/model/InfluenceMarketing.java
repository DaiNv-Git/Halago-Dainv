package com.example.halagodainv.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "influence_marketing")
public class InfluenceMarketing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "link_youtobe")
    private String linkYoutobe;
    @Column(name = "order")
    private int order;
}
