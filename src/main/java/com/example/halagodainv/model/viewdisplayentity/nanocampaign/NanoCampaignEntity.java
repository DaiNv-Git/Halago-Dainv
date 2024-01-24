package com.example.halagodainv.model.viewdisplayentity.nanocampaign;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "nano_campaign")
public class NanoCampaignEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nameFile;
    private String img;
}
