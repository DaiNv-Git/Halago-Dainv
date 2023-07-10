package com.example.halagodainv.model;

import com.fasterxml.jackson.annotation.JsonFormat;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;import java.util.Date;
@Entity
@Table(name = "api")
public class Api {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name_api")
    private String nameApi;
    @JsonFormat(pattern = "dd-MM-yyyy",timezone = "Asia/Ho_Chi_Minh")
    @Column(name = "created")
    private Date created;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameApi() {
        return nameApi;
    }

    public void setNameApi(String nameApi) {
        this.nameApi = nameApi;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
