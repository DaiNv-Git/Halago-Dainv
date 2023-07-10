package com.example.halagodainv.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "book_kols_language")
public class BookKolsLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "id_book_kols")
    private int idBookKols;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "language_key")
    private String languageKey;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBookKols() {
        return idBookKols;
    }

    public void setIdBookKols(int idBookKols) {
        this.idBookKols = idBookKols;
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

    public String getLanguageKey() {
        return languageKey;
    }

    public void setLanguageKey(String languageKey) {
        this.languageKey = languageKey;
    }
}
