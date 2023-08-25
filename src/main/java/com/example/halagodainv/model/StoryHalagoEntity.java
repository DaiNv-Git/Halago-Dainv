package com.example.halagodainv.model;

import lombok.Data;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;

@Data
@Entity
@Table(name = "story")
public class StoryHalagoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "image")
    private String image;
    @Column(name = "content")
    private String content;
    @Column(name = "content_en")
    private String contentEN;


}
