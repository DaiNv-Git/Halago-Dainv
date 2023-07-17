package com.example.halagodainv.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "groups")
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "group_name")
    private String groupName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "link")
    private String link;
    @Column(name = "member_total")
    private String memTotal;
    @Column(name = "expense")
    private String expense;
    @Column(name = "created")
    private Date created;
    @Column(name = "industry_id")
    private int industryId;
}
