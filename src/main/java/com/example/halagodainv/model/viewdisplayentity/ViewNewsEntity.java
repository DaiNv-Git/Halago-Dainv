package com.example.halagodainv.model.viewdisplayentity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "view_news")
public class ViewNewsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "header")
    private String herder;
    @Column(name = "body")
    private String body;
    @Column(name = "footer")
    private String footer;
    @Column(name = "image1")
    private String image1;
    @Column(name = "image2")
    private String image2;
    @Column(name = "topic_id")
    private Long topicId;
    @Column(name = "create_date")
    private Date createDate;
}
