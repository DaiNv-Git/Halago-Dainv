package com.example.halagodainv.model;

import com.example.halagodainv.model.campaign.CampaignEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "image_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "image_product")
    private String imageProduct;
    @ManyToOne
    private CampaignEntity campaignEntity;
}
