package com.example.halagodainv.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import utils.DateUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "influencer")
public class Influencer {
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
    @Column(name = "birthday")
    @JsonFormat(pattern = "dd-MM-yyyy",timezone = "Asia/Ho_Chi_Minh")
    private Date birthday;
    @Column(name = "address")
    private String address;
    @Column(name = "sex")
    private int sex;
    @Column(name = "career")
    private int career;
    @Column(name = "city")
    private int city;
    @Column(name = "url_fb")
    private String urlFb;
    @Column(name = "marital_status")
    private int maritalStatus;
    @Column(name = "description")
    private String description;
    @Column(name = "bank_account")
    private String bankAccount;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "status")
    private int status;
    @Column(name = "sale_experience")
    private String saleExpertience;
    @Column(name = "industry_id")
    private String industryId;
    @Column(name = "average_interact")
    private String averageInteract;
    @Column(name = "types_interaction")
    private String typesInteraction;
    @Column(name = "channel_interaction")
    private String channelInteraction;
    @Column(name = "fb_id")
    private String fbId;
    @Column(name = "created")
    @JsonFormat(pattern = "dd-MM-yyyy",timezone = "Asia/Ho_Chi_Minh")
    private Date created;
    @Column(name = "avatar")
    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = DateUtils.parseStringToDate(birthday);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getCareer() {
        return career;
    }

    public void setCareer(int career) {
        this.career = career;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public String getUrlFb() {
        return urlFb;
    }

    public void setUrlFb(String urlFb) {
        this.urlFb = urlFb;
    }

    public int getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(int maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSaleExpertience() {
        return saleExpertience;
    }

    public void setSaleExpertience(String saleExpertience) {
        this.saleExpertience = saleExpertience;
    }


    public String getAverageInteract() {
        return averageInteract;
    }

    public void setAverageInteract(String averageInteract) {
        this.averageInteract = averageInteract;
    }

    public String getIndustryId() {
        return industryId;
    }

    public void setIndustryId(String industryId) {
        this.industryId = industryId;
    }

    public String getTypesInteraction() {
        return typesInteraction;
    }

    public void setTypesInteraction(String typesInteraction) {
        this.typesInteraction = typesInteraction;
    }

    public String getChannelInteraction() {
        return channelInteraction;
    }

    public void setChannelInteraction(String channelInteraction) {
        this.channelInteraction = channelInteraction;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

}
