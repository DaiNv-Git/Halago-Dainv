package com.example.halagodainv.model.viewdisplayentity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "home_page")
@Entity
public class HomepageEntitty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "domestic_brands")
    private String domesticBrands;
    @Column(name = "foreign_brands")
    private String foreignBrands;
    @Column(name = "successful_campaign")
    private String successfulCampaign;
    @Column(name = "kOLs_informational")
    private String kOLsInformational;
}
