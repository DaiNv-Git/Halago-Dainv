package com.example.halagodainv.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "book_kols_language")
@Data
public class BookKolsLanguageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "title_en")
    private String titleEN;
    @Column(name = "interact_en")
    private String interactEN;
    @Column(name = "ratio_interact_en")
    private String ratioInteractEN;
    @Column(name = "kol_id")
    private long kolIdd;
}
