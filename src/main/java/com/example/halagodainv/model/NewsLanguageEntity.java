package com.example.halagodainv.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "news_language")
@Data
public class NewsLanguageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lang")
    int idLang;
    @Column(name = "title")
    String title;
    @Column(name = "description")
    String description;
    @Column(name = "content")
    String content;
    @Column(name = "header")
    private String herder;
    @Column(name = "body")
    private String body;
    @Column(name = "footer")
    private String footer;
    @Column(name = "language")
    String language;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "new_id")
    private NewsEntity newsEntity;
}
