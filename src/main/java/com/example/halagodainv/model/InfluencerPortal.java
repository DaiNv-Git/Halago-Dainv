package com.example.halagodainv.model;


import javax.persistence.*;

@Entity
@Table(name = "influencer_portal")
public class InfluencerPortal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "img")
    private String img;
    @Column(name = "type")
    private int type;
    @Column(name = "content_third")
    private String contentThird;
    @Column(name = "content_second")
    private String contentSecond;
    @Column(name = "status")
    private int status;
    @Column(name = "title_seo")
    private String titleSeo;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getTitleSeo() {
        return titleSeo;
    }

    public void setTitleSeo(String titleSeo) {
        this.titleSeo = titleSeo;
    }
}
