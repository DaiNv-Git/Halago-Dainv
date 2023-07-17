package com.example.halagodainv.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "social_network_influencer ")

public class SocialNetworkInfluencer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer influencerID;
    private String link;
    private Integer channelID;
    private Double follower;
    private Double expense;

    // Add constructors, getters, and setters
}