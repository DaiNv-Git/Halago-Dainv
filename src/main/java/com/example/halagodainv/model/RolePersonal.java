package com.example.halagodainv.model;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tbl_role_personal")
@Data
public class RolePersonal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idRolePersonal;
    @Column(name = "id_personal")
    private int idPersonal;
    @Column(name = "id_role")
    private int idRole;
    @Column(name = "status")
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
