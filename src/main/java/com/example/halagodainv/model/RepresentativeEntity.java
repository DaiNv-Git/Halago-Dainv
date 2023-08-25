package com.example.halagodainv.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "representative")
@Data
public class RepresentativeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "img")
    private String img;
    @Column(name = "name")
    private String name;
    @Column(name = "content")
    private String content;
    @Column(name = "reach")
    private String reach;
    @Column(name = "interactions")
    private String interactions;
    @Column(name = "interactionRate")
    private String interactionRate;
    @Column(name = "nameEN")
    private String nameEN;
    @Column(name = "contentEN")
    private String contentEN;
}
