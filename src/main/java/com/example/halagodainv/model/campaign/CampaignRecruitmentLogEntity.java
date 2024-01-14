package com.example.halagodainv.model.campaign;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "campaign_recruitment_log")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignRecruitmentLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idInflu;
    private int idCampaign;
    private Boolean isCheckAccept;
}
