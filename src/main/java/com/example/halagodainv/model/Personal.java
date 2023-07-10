package com.example.halagodainv.model;

import com.example.halagodainv.config.Constant;
import org.springframework.lang.Nullable;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "tbl_personal")
public class Personal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personal")
    private int idPersonal;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "introduce_code_user")
    private String introduceCodeUser;
    @Column(name = "birthday")
    private String birthDay;
    @Column(name = "gender")
    private String gender;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "cmnd")
    private String cmnd;
    @Column(name = "join_date")
    private String joinDate;
    @Column(name = "bank_account")
    private String bankAccount;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "bank_branch")
    private String bankBranch;
    @Column(name = "status")
    private int status;
    @Column(name = "password")
    private String password;
    @Column(name = "regency")
    private String regency;
    @Column(name = "reference_id")
    @Nullable
    private int referenceId;
    @Column(name = "fb_id")
    private String fbID;
    @Column(name = "active")
    private int active;
    @Column(name = "group_project")
    private String groupProject;
    @Column(name = "id_role")
    private int idRole;
    @Column(name = "image_cmnd")
    private String imageCmnd;

//    @Transient
//    private ArrayList<String> imageCmndList;
//
//    public ArrayList<String> getImageCmndList() {
//        return StringUtils.stringToStringArray(imageCmnd);
//    }

    public String getImageCmnd() {
        if (imageCmnd != null) {
            return Constant.IMAGE_DOMAIN + imageCmnd;
        } else return null;
    }

    public void setImageCmnd(String imageCmnd) {
        this.imageCmnd = imageCmnd;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getGroupProject() {
        return groupProject;
    }

    public void setGroupProject(String groupProject) {
        this.groupProject = groupProject;
    }

    public int getActive() {
        return active;
    }

    public int isActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getFbID() {
        return fbID;
    }

    public void setFbID(String fbID) {
        this.fbID = fbID;
    }

    public int getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(int referenceId) {
        this.referenceId = referenceId;
    }


    public String getPassword() {
        return password;
    }

    public String setPassword(String password) {
        this.password = password;
        return password;
    }

    public int getIdPersonal() {
        return idPersonal;
    }

    public int setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
        return idPersonal;
    }

    public String getAvatar() {
        if (avatar != null) {
            return Constant.IMAGE_DOMAIN + avatar;
        } else return null;

    }

    public String setAvatar(String avatar) {
        this.avatar = avatar;
        return avatar;
    }

    public String getFullName() {
        return fullName;
    }

    public String setFullName(String fullName) {
        this.fullName = fullName;
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String setEmail(String email) {
        this.email = email;
        return email;
    }

    public String getIntroduceCodeUser() {
        return introduceCodeUser;
    }

    public void setIntroduceCodeUser(String introduceCodeUser) {
        this.introduceCodeUser = introduceCodeUser;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public String setBirthDay(String birthDay) {
        this.birthDay = birthDay;
        return birthDay;
    }

    public String getGender() {
        return gender;
    }

    public String setGender(String gender) {
        this.gender = gender;
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String setAddress(String address) {
        this.address = address;
        return address;
    }

    public String getCmnd() {
        return cmnd;
    }

    public String setCmnd(String cmnd) {
        this.cmnd = cmnd;
        return cmnd;
    }

    public String getPhone() {
        return phone;
    }

    public String setPhone(String phone) {
        this.phone = phone;
        return phone;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public String setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
        return bankAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public String setBankName(String bankName) {
        this.bankName = bankName;
        return bankName;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public String setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
        return bankBranch;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRegency() {
        return regency;
    }

    public void setRegency(String regency) {
        this.regency = regency;
    }
}
