package com.example.halagodainv.model;



import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "function")
@Data
public class Function {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "function_name")
    private String functionName;
    @Column(name = "status")
    private int status;
}
