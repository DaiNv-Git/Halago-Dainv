package com.example.halagodainv.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "image_solution")
public class ImageLiveStreamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "image")
    private String image;
    @Column(name = "image_name_vn")
    private String imageNameVN;
    @Column(name = "image_name_en")
    private String imageNameEN;
    @Column(name = "solution_live_stream_id")
    private long solutionLiveStreamId;
}
