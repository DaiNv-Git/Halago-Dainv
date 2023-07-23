package com.example.halagodainv.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "industry")
public class IndustryEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "industry_name")
    private String industryName;
}
