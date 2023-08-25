package com.example.halagodainv.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "best_kol")
public class BestKolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "image")
    private String image;
    @Column(name = "name")
    private String name;
    @Column(name = "job")
    private String job;
    @Column(name = "nameEN")
    private String nameEN;
    @Column(name = "jobEN")
    private String jobEN;
}
