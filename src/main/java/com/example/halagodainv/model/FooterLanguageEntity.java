package com.example.halagodainv.model;

import javax.persistence.*;

@Entity
@Table(name = "footer_language")
public class FooterLanguageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "address")
    private String address;
    @Column(name = "company")
    private String company;
    @Column(name = "language_key")
    private String languageKey;
    @Column(name = "id_footer")
    private int idFooter;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLanguageKey() {
        return languageKey;
    }

    public void setLanguageKey(String languageKey) {
        this.languageKey = languageKey;
    }

    public int getIdFooter() {
        return idFooter;
    }

    public void setIdFooter(int idFooter) {
        this.idFooter = idFooter;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
