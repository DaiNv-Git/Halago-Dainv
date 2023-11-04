package com.example.halagodainv.model;

import lombok.Data;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "brand")
@Data
public class BrandEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "brand_name")
    private String brandName;
    @Column(name = "brand_email")
    private String brandEmail;
    @Column(name = "brand_phone")
    private String brandPhone;
    @Column(name = "status")
    private int status;
    @Column(name = "representative_name")
    private String representativeName;
    @Column(name = "website")
    private String website;
    @Column(name = "description")
    private String description;
    @Column(name = "created")
    private Date created;
    @Column(name = "fb_id")
    private String fbId;
    @Column(name = "logo")
    private String logo;
    @Column(name = "partner_id")
    private Integer partnerId;
    @Column(name = "filming_tv_commercials")
    private boolean filmingTVCCommercials;
    @Column(name = "live_stream")
    private boolean liveStream;
    @Column(name = "review")
    private boolean review;
    @Column(name = "orther")
    private boolean orther;
    @Column(name = "event")
    private boolean event;
    @Column(name = "brand_ambassador")
    private boolean brandAmbassador;
}
