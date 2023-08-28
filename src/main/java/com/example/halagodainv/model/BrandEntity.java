package com.example.halagodainv.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
}
