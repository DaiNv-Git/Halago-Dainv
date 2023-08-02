package com.example.halagodainv.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "story_typical_activities")
public class StoryTypicalActivitiesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "image")
    private String image;
    @Column(name = "title")
    private String title;
    @Column(name = "title_en")
    private String titleEN;
    @Column(name = "content")
    private String content;
    @Column(name = "content_en")
    private String contentEN;
}
