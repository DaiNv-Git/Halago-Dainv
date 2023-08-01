package com.example.halagodainv.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "best_kol_celebrity")
public class BestKolImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "image")
    private String image;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "age")
    private Integer age;
    @Column(name = "career")
    private String career;
    @Column(name = "stage_name")
    private String stageName;
    @Column(name = "name_en")
    private String nameEN;
    @Column(name = "description_en")
    private String descriptionEN;
    @Column(name = "career_en")
    private String careerEN;
}
