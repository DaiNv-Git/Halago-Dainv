package com.example.halagodainv.model.viewdisplayentity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "free_consultation")
public class FreeConsultationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "note")
    private String note;
    @Column(name = "website")
    private String website;
    @Column(name = "advertisement_vtc")
    private Boolean isAdvertisementVTC;
    @Column(name = "brand_ambassador")
    private Boolean isBrandAmbassador;
    @Column(name = "event")
    private Boolean isEvent;
    @Column(name = "live_stream")
    private Boolean isLiveStream;
    @Column(name = "review")
    private Boolean isReview;
    @Column(name = "other")
    private Boolean isOther;
    @Column(name = "created")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
    private Date created;
}
