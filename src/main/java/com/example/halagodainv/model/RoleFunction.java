package com.example.halagodainv.model;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "role_function")
@Data
public class RoleFunction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "id_function")
    private String idFunction;
    @Column(name = "id_role")
    private int idRole;

}
