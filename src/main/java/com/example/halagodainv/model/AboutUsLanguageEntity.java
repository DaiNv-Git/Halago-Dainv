package com.example.halagodainv.model;



import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "about_us_language")
@Data
public class AboutUsLanguageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lang")
    private int idLang;
    @Column(name = "id_about")
    private int idAbout;
    @Column(name = "title_en")
    private String titleEn;
    @Column(name = "content_en")
    private String contentEn;
    @Column(name = "language")
    private String language;
}
