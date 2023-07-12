package com.example.halagodainv.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "image_product")
@Data
public class ImageProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "image_product")
    private String imageProduct;
    @Column(name = "campaign_id")
    private int campaignId;
    @ManyToOne
    private CampaignEntity campaignEntity;
}
