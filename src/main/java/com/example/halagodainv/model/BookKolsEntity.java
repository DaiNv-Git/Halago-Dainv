package com.example.halagodainv.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "book_kols")
@Data
public class BookKolsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "image_kol1")
    private String imageKol1;
    @Column(name = "image_kol2")
    private String imageKol2;
    @Column(name = "introduce")
    private String introduce;
    @Column(name = "introduce_detail")
    private String introduceDetail;
    @Column(name = "approach")
    private String approach;
    @Column(name = "interact")
    private String interact;
    @Column(name = "ratio_interact")
    private String ratioInteract;
}
