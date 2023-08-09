package com.example.halagodainv.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "attach_file")
@Data
public class AttachFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "tyle_file")
    private String typeFIle;
    @Column(name = "url_file")
    private byte[] urlFile;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "status")
    private int status;

}
