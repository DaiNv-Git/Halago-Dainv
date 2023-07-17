package com.example.halagodainv.response.Influencer;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;

public class InfluencerResponse {
    private int id;

    private String name;

    private String phone;

    private String email;

    @JsonFormat(pattern = "dd-MM-yyyy",timezone = "Asia/Ho_Chi_Minh")
    private Date birthday;

    private String address;

    private int sex;

    private int career;

    private int city;

    private String urlFb;

    private int maritalStatus;

    private int maritalStatusName;

    private String description;

    private String bankAccount;

    private String bankName;

    private int status;

    private String statusName;

    private String saleExpertience;

    private ArrayList<Integer> industryId;

    private String averageInteract;

    private ArrayList<Integer> typesInteraction;

    private ArrayList<Integer> channelInteraction;

    private String token;

    private String fbId;

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    @JsonFormat(pattern = "dd-MM-yyyy",timezone = "Asia/Ho_Chi_Minh")
    private Date created;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public int getMaritalStatusName() {
        return maritalStatusName;
    }

    public void setMaritalStatusName(int maritalStatusName) {
        this.maritalStatusName = maritalStatusName;
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

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
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

    public ArrayList<Integer> getIndustryId() {
        return industryId;
    }

    public void setIndustryId(ArrayList<Integer> industryId) {
        this.industryId = industryId;
    }

    public ArrayList<Integer> getTypesInteraction() {
        return typesInteraction;
    }

    public void setTypesInteraction(ArrayList<Integer> typesInteraction) {
        this.typesInteraction = typesInteraction;
    }

    public ArrayList<Integer> getChannelInteraction() {
        return channelInteraction;
    }

    public void setChannelInteraction(ArrayList<Integer> channelInteraction) {
        this.channelInteraction = channelInteraction;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
