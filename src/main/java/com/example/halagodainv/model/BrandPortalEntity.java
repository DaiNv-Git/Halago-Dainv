package com.example.halagodainv.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "brand_portal ")
@Data
public class BrandPortalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private int status;
    @Column(name = "type")
    private int type;
    @Column(name = "total_like")
    private String totalLike;
    @Column(name = "total_comment")
    private String totalComment;
    @Column(name = "total_share")
    private String totalShare;
    @Column(name = "cus_name")
    private String cusName;
    @Column(name = "position")
    private String position;
    @Column(name = "img")
    private String img;
    @Column(name = "title_seo")
    private String titleSeo;
}
