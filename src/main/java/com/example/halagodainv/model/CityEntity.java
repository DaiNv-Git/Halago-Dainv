package com.example.halagodainv.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "city")
@Data
public class CityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private int cityId;
    @Column(name = "city_name")
    private String cityName;
    @Column(name = "sort")
    private int sort;
}
