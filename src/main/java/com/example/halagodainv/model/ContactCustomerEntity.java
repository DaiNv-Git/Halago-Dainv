package com.example.halagodainv.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "contact_customer")
@Data
public class ContactCustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "note")
    private String note;
    @Column(name = "created")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Ho_Chi_Minh")
    private Date created;
}
