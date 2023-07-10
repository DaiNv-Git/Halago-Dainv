package com.example.halagodainv.model;

import com.fasterxml.jackson.annotation.JsonFormat;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "brand")
public class Brand implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "brand_name")
    private String brandName;
    @Column(name = "brand_email")
    private String brandEmail;
    @Column(name = "brand_phone")
    private String brandPhone;
    @Column(name = "status")
    private int status;
    @Column(name = "representative_name")
    private String representativeName;
    @Column(name = "website")
    private String website;
    @Column(name = "description")
    private String description;
    @Column(name = "created")
    @JsonFormat(pattern = "dd-MM-yyyy",timezone = "Asia/Ho_Chi_Minh")
    private Date created;
    @Column(name = "fb_id")
    private String fbId;
    @Column(name = "logo")
    private String logo;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandEmail() {
        return brandEmail;
    }

    public void setBrandEmail(String brandEmail) {
        this.brandEmail = brandEmail;
    }

    public String getBrandPhone() {
        return brandPhone;
    }

    public void setBrandPhone(String brandPhone) {
        this.brandPhone = brandPhone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRepresentativeName() {
        return representativeName;
    }

    public void setRepresentativeName(String representativeName) {
        this.representativeName = representativeName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
