package com.example.halagodainv.model.campaign;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "work_category_campaign")
@Data
public class WorkCategoryCampaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String categoryName;
}
