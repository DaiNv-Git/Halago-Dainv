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
    @Column(name = "live")
    private String live;
    @Column(name = "brand")
    private String brand;
    @Column(name = "money")
    private String money;
    @Column(name = "image_sale1")
    private String imageSale1;
    @Column(name = "image_sale2")
    private String imageSale2;
}
