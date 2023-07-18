package com.example.halagodainv.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "footer")
@Data
public class FooterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "address")
    private String address;
    @Column(name = "email")
    private String email;
    @Column(name = "hotline")
    private String hotline;
    @Column(name = "facebook")
    private String facebook;
    @Column(name = "zalo")
    private String zalo;
    @Column(name = "company")
    private String company;
    @Column(name = "youtube")
    private String youtube;
    @Column(name = "tiktok")
    private String tiktok;
    @Column(name = "instagram")
    private String instagram;
    @Column(name = "tax_code")
    private String taxCode;
}
