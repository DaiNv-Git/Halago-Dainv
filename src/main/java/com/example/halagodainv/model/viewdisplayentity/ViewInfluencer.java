package com.example.halagodainv.model.viewdisplayentity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Table(name = "view_influencer")
@Data
@Entity
public class ViewInfluencer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;
    private String contentVN;
}
