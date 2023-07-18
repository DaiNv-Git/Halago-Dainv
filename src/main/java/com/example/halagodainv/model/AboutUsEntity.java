package com.example.halagodainv.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "about_us")
public class AboutUsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "language")
    private String language;
    @Column(name = "type")
    private int type;
}
