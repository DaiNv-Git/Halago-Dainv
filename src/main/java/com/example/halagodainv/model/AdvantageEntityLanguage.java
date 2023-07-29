package com.example.halagodainv.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "advantage_language")
public class AdvantageEntityLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "advan_id")
    private long advanId;
    @Column(name = "language")
    private String language;
}
