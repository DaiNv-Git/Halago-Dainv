package com.example.halagodainv.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tbl_fb")
public class Facebook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "fb_id")
    private String fbID;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "photo")
    private String photo;
    @Column(name = "name")
    private String name;
    @Column(name = "introduce_code")
    private String introduceCode;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "bank_branch")
    private String bankBranch;
    @Column(name = "bank_account")
    private int bankAccount;
    @Column(name = "cmnd")
    private String cmnd;
    @Column(name = "active")
    private int active;
    @Column(name = "reference_id")
    private int referenceId;
    @Column(name = "birthday")
    private int birthday;
}
