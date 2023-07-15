package com.example.halagodainv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "page")
@AllArgsConstructor
@NoArgsConstructor
public class PageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;
    @Column(name = "name_page")
    private String namePage;
    @Column(name = "phone")
    private String phone;
    @Column(name = "link")
    private String link;
    @Column(name = "follower")
    private String follower;
    @Column(name = "expense")
    private String expense;
    @Column(name = "created")
    private Date created;
    @Column(name = "industry_id")
    private int industryId;
}
