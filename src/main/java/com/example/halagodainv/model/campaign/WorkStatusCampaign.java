package com.example.halagodainv.model.campaign;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "work_staus_campaign")
@Data
public class WorkStatusCampaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String statusName;
}
