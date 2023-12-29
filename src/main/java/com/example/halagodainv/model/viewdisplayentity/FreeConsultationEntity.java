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
    private boolean isAdvertisementVTC;
    @Column(name = "brand_ambassador")
    private boolean isBrandAmbassador;
    @Column(name = "event")
    private boolean isEvent;
    @Column(name = "live_stream")
    private boolean isLiveStream;
    @Column(name = "review")
    private boolean isReview;
    @Column(name = "other")
    private boolean isOther;
    @Column(name = "created")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
    private Date created;
}
