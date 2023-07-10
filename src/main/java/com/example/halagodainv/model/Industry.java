package com.example.halagodainv.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "industry")
public class Industry {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "industry_name")
    private String industryName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }
}
