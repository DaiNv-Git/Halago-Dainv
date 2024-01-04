package com.example.halagodainv.model.campaign;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
    private int workStatus;
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
    @Column(name = "image1")
    private String image1;
    @Column(name = "image2")
    private String image2;
    @Column(name = "image3")
    private String image3;
    @Column(name = "campaign_name_en")
    private String campaignNameEN;
    @Column(name = "condition_apply_en")
    private String conditionApplyEN;
    @Column(name = "method_en")
    private String methodEN;
    @Column(name = "outstanding_product_en")
    private String outstandingProductEN;
    @Column(name = "content_en")
    private String contentEN;
    @Column(name = "other_en")
    private String otherEN;
}
