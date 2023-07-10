package com.example.halagodainv.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import utils.DateUtils;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "campaign")
public class Campaign implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "campaign_name")
    private String campaignName;
    @Column(name = "date_start")
    @JsonFormat(pattern = "dd-MM-yyyy",timezone = "Asia/Ho_Chi_Minh")
    private Date dateStart;
    @Column(name = "date_end")
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Ho_Chi_Minh")
    private Date dateEnd;
    @Column(name = "description")
    private String description;
    @Column(name = "rewards")
    private String rewards;
    @Column(name = "status")
    private int status;
    @Column(name = "brand_name")
    private String brandName;
    @Column(name = "content")
    private String content;
    @Column(name = "created")
    @JsonFormat(pattern = "dd-MM-yyyy",timezone = "Asia/Ho_Chi_Minh")
    private Date created;
    @Column(name = "img")
    private String img;
    @Column(name = "img_product")
    private String imgProduct;
    @Column(name = "id_brand")
    private int idBrand;
    @Column(name = "industry_id")
    private int industryId;

    public int getIndustryId() {
        return industryId;
    }

    public void setIndustryId(int industryId) {
        this.industryId = industryId;
    }

    public String getImgProduct() {
        return imgProduct;
    }

    public void setImgProduct(String imgProduct) {
        this.imgProduct = imgProduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = DateUtils.parseStringToDate(dateStart);;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = DateUtils.parseStringToDate(dateEnd);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRewards() {
        return rewards;
    }

    public void setRewards(String rewards) {
        this.rewards = rewards;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(int idBrand) {
        this.idBrand = idBrand;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
