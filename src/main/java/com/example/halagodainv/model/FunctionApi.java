package com.example.halagodainv.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "function_api")
public class FunctionApi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "id_api")
    private String idApi;
    @Column(name = "id_function")
    private int idFunction;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdApi() {
        return idApi;
    }

    public void setIdApi(String idApi) {
        this.idApi = idApi;
    }

    public int getIdFunction() {
        return idFunction;
    }

    public void setIdFunction(int idFunction) {
        this.idFunction = idFunction;
    }
}
