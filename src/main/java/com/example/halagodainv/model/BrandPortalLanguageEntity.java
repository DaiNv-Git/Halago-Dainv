package com.example.halagodainv.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "brand_portal_language")
@Data
public class BrandPortalLanguageEntity {
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
}
