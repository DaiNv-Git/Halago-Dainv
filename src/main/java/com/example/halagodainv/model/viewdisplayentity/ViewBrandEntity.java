package com.example.halagodainv.model.viewdisplayentity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "view_brand")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ViewBrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "logo_brand")
    private String logoBrand;
    @Column(name = "name")
    private String name;
    @Column(name = "position_id")
    private Integer positionId;
    @Column(name = "description_vn")
    private String descriptionVN;
    @Column(name = "description_en")
    private String descriptionEN;

}
