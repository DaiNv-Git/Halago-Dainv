package com.example.halagodainv.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
@Entity
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduceCode() {
        return introduceCode;
    }

    public void setIntroduceCode(String introduceCode) {
        this.introduceCode = introduceCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public int getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(int bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(int referenceId) {
        this.referenceId = referenceId;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public String getFbID() {
        return fbID;
    }

    public void setFbID(String fbID) {
        this.fbID = fbID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
