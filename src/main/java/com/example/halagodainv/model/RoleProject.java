package com.example.halagodainv.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "tbl_role_project")
public class RoleProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role_project")
    private int idRoleProject;
    @Column(name = "name")
    private String name;

    public int getIdRoleProject() {
        return idRoleProject;
    }

    public void setIdRoleProject(int idRoleProject) {
        this.idRoleProject = idRoleProject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
