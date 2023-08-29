package com.example.halagodainv.model.viewdisplayentity;

import lombok.Data;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;

@Entity
@Table(name = "brand_give")
@Data
public class BrandGiveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "logo_brand")
    private String logoBrand;
    @Column(name = "author_avatar")
    private String authorAvatar;
    @Column(name = "author_name")
    private String authorName;
    @Column(name = "position")
    private String position;
    @Column(name = "content")
    private String content;
    @Column(name = "content_en")
    private String contentEN;
}
