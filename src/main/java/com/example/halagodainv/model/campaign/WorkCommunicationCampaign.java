package com.example.halagodainv.model.campaign;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "work_communication_campaign")
@Data
public class WorkCommunicationCampaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String communicationName;
}
