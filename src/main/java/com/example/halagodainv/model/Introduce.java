package com.example.halagodainv.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "introduce")
public class Introduce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "image")
    private String img;
    @Column(name = "status")
    private int status;
    @Column(name = "content")
    private String content;
    @Column(name = "content_first")
    private String contentFirst;
    @Column(name = "content_second")
    private String contentSecond;
    @Column(name = "img_second")
    private String imgSecond;
    @Column(name = "img_third")
    private String imgThird;
    @Column(name = "img_four")
    private String imgFour;
    @Column(name = "img_five")
    private String imgFive;
    @Column(name = "type")
    private int type;
    @Column(name = "total_influencer")
    private String totalInfluencer;
    @Column(name = "total_kols")
    private String totalKols;
    @Column(name = "total_star")
    private String totalStar;
    @Column(name = "img_six")
    private String imgSix;
    @Column(name = "img_seven")
    private String imgSeven;
    @Column(name = "img_eight")
    private String imgEight;
    @Column(name = "img_nine")
    private String imgNine;
    @Column(name = "img_ten")
    private String imgTen;
    @Column(name = "img_eleven")
    private String imgEleven;
    @Column(name = "img_twelve")
    private String imgTwelve;



    public String getImgSix() {
        return imgSix;
    }

    public void setImgSix(String imgSix) {
        this.imgSix = imgSix;
    }

    public String getImgSeven() {
        return imgSeven;
    }

    public void setImgSeven(String imgSeven) {
        this.imgSeven = imgSeven;
    }

    public String getImgEight() {
        return imgEight;
    }

    public void setImgEight(String imgEight) {
        this.imgEight = imgEight;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getContentFirst() {
        return contentFirst;
    }

    public void setContentFirst(String contentFirst) {
        this.contentFirst = contentFirst;
    }

    public String getContentSecond() {
        return contentSecond;
    }

    public void setContentSecond(String contentSecond) {
        this.contentSecond = contentSecond;
    }

    public String getImgSecond() {
        return imgSecond;
    }

    public void setImgSecond(String imgSecond) {
        this.imgSecond = imgSecond;
    }

    public String getImgThird() {
        return imgThird;
    }

    public void setImgThird(String imgThird) {
        this.imgThird = imgThird;
    }

    public String getImgFour() {
        return imgFour;
    }

    public void setImgFour(String imgFour) {
        this.imgFour = imgFour;
    }

    public String getImgFive() {
        return imgFive;
    }

    public void setImgFive(String imgFive) {
        this.imgFive = imgFive;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTotalInfluencer() {
        return totalInfluencer;
    }

    public void setTotalInfluencer(String totalInfluencer) {
        this.totalInfluencer = totalInfluencer;
    }

    public String getTotalKols() {
        return totalKols;
    }

    public void setTotalKols(String totalKols) {
        this.totalKols = totalKols;
    }

    public String getTotalStar() {
        return totalStar;
    }

    public void setTotalStar(String totalStar) {
        this.totalStar = totalStar;
    }

    public String getImgNine() {
        return imgNine;
    }

    public void setImgNine(String imgNine) {
        this.imgNine = imgNine;
    }

    public String getImgTen() {
        return imgTen;
    }

    public void setImgTen(String imgTen) {
        this.imgTen = imgTen;
    }

    public String getImgEleven() {
        return imgEleven;
    }

    public void setImgEleven(String imgEleven) {
        this.imgEleven = imgEleven;
    }

    public String getImgTwelve() {
        return imgTwelve;
    }

    public void setImgTwelve(String imgTwelve) {
        this.imgTwelve = imgTwelve;
    }
}
