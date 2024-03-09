package com.example.halagodainv.model.authen;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "authen_password")
public class AuthenPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String code;
}
