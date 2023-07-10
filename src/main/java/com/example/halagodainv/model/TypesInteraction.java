package com.example.halagodainv.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "types_interaction")
@Data
public class TypesInteraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name_interaction")
    private String nameInteraction;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameInteraction() {
        return nameInteraction;
    }

    public void setNameInteraction(String nameInteraction) {
        this.nameInteraction = nameInteraction;
    }
}
