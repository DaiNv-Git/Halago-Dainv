package com.example.halagodainv.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "news")
@Builder
public class NewsEntity implements Serializable {
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
    private Integer productId;
    @Column(name = "is_product")
    private Integer isProduct;
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
    @Column(name = "representative_id")
    private Long representativeId;
    @Column(name = "news_from_kol")
    private Long newsFromKol;
    @JsonIgnore
    @OneToMany(mappedBy = "newsEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<NewsLanguageEntity> imageBrandMains = new ArrayList<>();
}
