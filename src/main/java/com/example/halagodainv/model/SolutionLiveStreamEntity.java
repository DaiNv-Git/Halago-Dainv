package com.example.halagodainv.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "solution_live_stream")
public class SolutionLiveStreamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "session")
    private String session;
    @Column(name = "satisfied_brand")
    private String satisfied_brand;
    @Column(name = "sales")
    private String sales;
    @Column(name = "image_sale1")
    private String imageSale1;
    @Column(name = "image_sale2")
    private String imageSale2;
    @Column(name = "content_one")
    private String contentOne;
    @Column(name = "content_two")
    private String contentTwo;
    @Column(name = "contentThree")
    private String contentThree;
}
