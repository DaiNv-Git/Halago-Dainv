package com.example.halagodainv.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "users")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "password_hide")
    private String passwordHide;
    @Column(name = "role")
    private int role;
    @Column(name = "created")
    @JsonFormat(pattern = "dd-MM-yyyy",timezone = "Asia/Ho_Chi_Minh")
    private Date created;
    @Column(name = "status")
    private int status;
    @Column(name = "email")
    private String email;
}
