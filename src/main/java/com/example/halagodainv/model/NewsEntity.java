package com.example.halagodainv.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "news")
public class NewsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_news")
    int idNews;
    @Column(name = "thumbnail")
    String thumbnail;
    @Column(name = "created")
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Ho_Chi_Minh")
    private Date created;
    @Column(name = "type")
    int type;
    @Column(name = "title_seo")
    String titleSeo;
    @Column(name = "link_papers")
    String linkPapers;
    @Column(name = "topic_id")
    private Long topicId;
    @Column(name = "productId")
    private int productId;
    @Column(name = "is_product")
    private int isProduct;
    @Column(name = "tagId")
    private String tagId;
    @Column(name = "tag_name")
    private String tagName;
    @Column(name = "is_hot")
    private Boolean isHot;
    @Column(name = "author_name")
    private String authorName;
    @Column(name = "author_avatar")
    private String authorAvatar;
    @JsonIgnore
    @OneToMany(mappedBy = "newsEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<NewsLanguageEntity> imageBrandMains = new ArrayList<>();
}
