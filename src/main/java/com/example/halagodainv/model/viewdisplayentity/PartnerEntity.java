package com.example.halagodainv.model.viewdisplayentity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "partner")
public class PartnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "logo")
    private String logo;
    @Column(name = "partner_id")
    private Integer partnerId;
}
