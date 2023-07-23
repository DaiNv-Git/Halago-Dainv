package com.example.halagodainv.model;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "classify")
@Data
public class ClassifyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
}
