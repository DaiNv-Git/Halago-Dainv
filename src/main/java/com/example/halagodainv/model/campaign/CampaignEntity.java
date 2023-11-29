package com.example.halagodainv.model.campaign;


import com.example.halagodainv.model.ImageProductEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "campaign")
@Data
public class CampaignEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "campaign_name")
    private String campaignName;
    @Column(name = "date_start")
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Ho_Chi_Minh")
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
    private Date created;
    @Column(name = "img")
    private String img;
    @Column(name = "id_brand")
    private int idBrand;
    @Column(name = "industry")
    private String industry = "";
    @Column(name = "industry_id")
    private String industryId;
    @Column(name = "title_product")
    private String titleProduct;
    @Column(name = "title_campaign")
    private String titleCampaign;
    @Column(name = "communication")
    private String campaignCommunication;
    @Column(name = "work_status")
    private int campaignStatus;
    @Column(name = "category")
    private String campaignCategory;
    @OneToMany(mappedBy = "campaignEntity")
    List<ImageProductEntity> productEntityList = new ArrayList<>();
}
