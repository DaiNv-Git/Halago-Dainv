package com.example.halagodainv.model;

import javax.persistence.*;

@Entity
@Table(name = "influencer_portal_language")
public class InfluencerPortalLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "content_third")
    private String contentThird;
    @Column(name = "content_second")
    private String contentSecond;
    @Column(name = "language_key")
    private String languageKey;
    @Column(name = "id_influencer_portal")
    private int idInfluencerPortal;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentThird() {
        return contentThird;
    }

    public void setContentThird(String contentThird) {
        this.contentThird = contentThird;
    }

    public String getContentSecond() {
        return contentSecond;
    }

    public void setContentSecond(String contentSecond) {
        this.contentSecond = contentSecond;
    }

    public String getLanguageKey() {
        return languageKey;
    }

    public void setLanguageKey(String languageKey) {
        this.languageKey = languageKey;
    }

    public int getIdInfluencerPortal() {
        return idInfluencerPortal;
    }

    public void setIdInfluencerPortal(int idInfluencerPortal) {
        this.idInfluencerPortal = idInfluencerPortal;
    }
}
