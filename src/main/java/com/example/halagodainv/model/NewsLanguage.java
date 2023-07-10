package com.example.halagodainv.model;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "news_language")
@Data
public class NewsLanguage {
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
    @Column(name = "language")
    String language;
    @Column(name = "id_news")
    int idNews;

}
