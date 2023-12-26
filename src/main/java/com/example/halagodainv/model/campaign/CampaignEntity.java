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
    @Column(name = "brandName")
    private String brandName;
    @Column(name = "description")
    private String description;
    @Column(name = "created")
    private Date created;
    @Column(name = "img")
    private String img;
    @Column(name = "industry_id")
    private String industryId;
    @Column(name = "communication")
    private String campaignCommunication;
    @Column(name = "work_status")
    private int campaignStatus;
    @Column(name = "campaign_category")
    private String campaignCategory;
    @Column(name = "condition_apply")
    private String conditionApply;
    @Column(name = "method")
    private String method;
    @Column(name = "hashtag")
    private String hashtag;
    @Column(name = "outstanding_product")
    private String outstandingProduct;
    @Column(name = "content")
    private String content;
    @Column(name = "other")
    private String other;
    @Column(name = "time_deadline")
    private String timeDeadline;
    @OneToMany(mappedBy = "campaignEntity")
    List<ImageProductEntity> productEntityList = new ArrayList<>();
}
