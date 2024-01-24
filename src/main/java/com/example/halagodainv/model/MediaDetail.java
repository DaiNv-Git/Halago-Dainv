package com.example.halagodainv.model;

import javax.persistence.*;

@Entity
@Table(name = "MediaDetail")
public class MediaDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "image")
    private String image;
    @Column(name = "type")
    private int type;
}
