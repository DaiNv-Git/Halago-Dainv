package com.example.halagodainv.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "approve")
@Data
public class ApproveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "id_campaign")
    private int idCampaign;
    @Column(name = "id_influencer")
    private int idInfluencer;
    @Column(name = "status")
    private int status;
    @Column(name = "created")
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Ho_Chi_Minh")
    private Date created;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCampaign() {
        return idCampaign;
    }

    public void setIdCampaign(int idCampaign) {
        this.idCampaign = idCampaign;
    }

    public int getIdInfluencer() {
        return idInfluencer;
    }

    public void setIdInfluencer(int idInfluencer) {
        this.idInfluencer = idInfluencer;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
