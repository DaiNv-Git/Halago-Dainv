package com.example.halagodainv.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "about_us_language")
public class AboutUsLanguage {
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

    public int getIdLang() {
        return idLang;
    }

    public void setIdLang(int idLang) {
        this.idLang = idLang;
    }

    public int getIdAbout() {
        return idAbout;
    }

    public void setIdAbout(int idAbout) {
        this.idAbout = idAbout;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getContentEn() {
        return contentEn;
    }

    public void setContentEn(String contentEn) {
        this.contentEn = contentEn;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
