package com.example.halagodainv.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "influencer_entity")
public class InfluencerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String influcerName;
    @Column(name = "history_created")
    private Date historyCreated;
    @Column(name = "created")
    private Date created;
    @Column(name = "sex")
    private boolean sex;
    @Column(name = "phone")
    private String phone;
    @Column(name = "yearOld")
    private String yearOld;
    @Column(name = "isFacebook")
    private boolean isFacebook;
    @Column(name = "linkFb")
    private String linkFb;
    @Column(name = "isTiktok")
    private boolean isTiktok;
    @Column(name = "linkTT")
    private String linkTT;
    @Column(name = "isInstagram")
    private boolean isInstagram;
    @Column(name = "linkIns")
    private String linkIns;
    @Column(name = "isYoutube")
    private boolean isYoutube;
    @Column(name = "linkYoutube")
    private String linkYoutube;
    @Column(name = "email")
    private String email;
    @Column(name = "industry")
    private String industry;
    @Column(name = "classify_id")
    private int classifyId;
    @Column(name = "province_id")
    private int provinceId;
    @Column(name = "address")
    private String address;
    @Column(name = "bank")
    private String bank;
    @Column(name = "account_number")
    private String accountNumber;

}
