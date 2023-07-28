package com.example.halagodainv.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "efficiency_optimization")
public class EfficiencyOptimizationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "language")
    private String language;
    @Column(name = "created")
    private Date created;
    @Column(name = "id_update")
    private long idUpdate;
}
