package com.example.halagodainv.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "story")
public class StoryHalagoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "image")
    private String img;
    @Column(name = "content")
    private String content;
    @Column(name = "content_en")
    private String contentEN;


}
