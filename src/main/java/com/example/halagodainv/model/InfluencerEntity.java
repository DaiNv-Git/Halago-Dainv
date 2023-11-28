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
    private Integer sex;
    @Column(name = "phone")
    private String phone;
    @Column(name = "yearOld")
    private String yearOld;
    @Column(name = "isFacebook")
    private boolean isFacebook;
    @Column(name = "isTiktok")
    private boolean isTiktok;
    @Column(name = "isInstagram")
    private boolean isInstagram;
    @Column(name = "isYoutube")
    private boolean isYoutube;
    @Column(name = "email")
    private String email;
    @Column(name = "industry")
    private String industry;
    @Column(name = "industry_name")
    private String industryName;
    @Column(name = "classify_id")
    private String classifyId;
    @Column(name = "classify_Name")
    private String classifyName;
    @Column(name = "province_id")
    private Integer provinceId;
    @Column(name = "address")
    private String address;
    @Column(name = "bank")
    private String bankId;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "age")
    private Integer age;

}
