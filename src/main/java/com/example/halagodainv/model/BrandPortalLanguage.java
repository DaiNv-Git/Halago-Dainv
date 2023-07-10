package com.example.halagodainv.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "brand_portal_language")
public class BrandPortalLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "cus_name")
    private String cusName;
    @Column(name = "position")
    private String position;
    @Column(name = "language_key")
    private String languageKey;
    @Column(name = "id_brand_portal")
    private int idBrandPortal;

    public String getLanguageKey() {
        return languageKey;
    }

    public void setLanguageKey(String languageKey) {
        this.languageKey = languageKey;
    }

    public int getIdBrandPortal() {
        return idBrandPortal;
    }

    public void setIdBrandPortal(int idBrandPortal) {
        this.idBrandPortal = idBrandPortal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
