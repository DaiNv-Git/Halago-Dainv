package com.example.halagodainv.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "book_kols")
public class BookKols {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "banner")
    private String banner;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "poster")
    private String poster;
    @Column(name = "img")
    private String img;
    @Column(name = "status")
    private int status;
    @Column(name = "total_register")
    private String totalRegister;
    @Column(name = "total_view")
    private String totalView;
    @Column(name = "total_duration_view")
    private String totalDurationView;
    @Column(name = "type")
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
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

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTotalRegister() {
        return totalRegister;
    }

    public void setTotalRegister(String totalRegister) {
        this.totalRegister = totalRegister;
    }

    public String getTotalView() {
        return totalView;
    }

    public void setTotalView(String totalView) {
        this.totalView = totalView;
    }

    public String getTotalDurationView() {
        return totalDurationView;
    }

    public void setTotalDurationView(String totalDurationView) {
        this.totalDurationView = totalDurationView;
    }
}
