package com.example.halagodainv.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "image_review")
public class ImageReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "image_review")
    private String imageReview;
    @Column(name = "link")
    private String link;
    @Column(name = "name_vn")
    private String nameVN;
    @Column(name = "name_en")
    private String nameEN;
}
