package com.example.halagodainv.model.viewdisplayentity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "view_news_language")
public class NewViewLanguageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "new_view_id")
    private int newViewId;
    @Column(name = "title_en")
    private String titleEN;
    @Column(name = "header_en")
    private String herderEN;
    @Column(name = "body_en")
    private String bodyEN;
    @Column(name = "footer_en")
    private String footerEN;
}
