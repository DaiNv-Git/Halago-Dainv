package com.example.halagodainv.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "career")
public class Career {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "career_name")
    private String careerName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCareerName() {
        return careerName;
    }

    public void setCareerName(String careerName) {
        this.careerName = careerName;
    }
}
