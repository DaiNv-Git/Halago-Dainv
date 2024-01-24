package com.example.halagodainv.model.viewdisplayentity.managekol;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "manage_kol")
public class ManageKolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String professionalManagement;
    private String img1;
    private String img2;
    private String img3;
    private String img4;
}
