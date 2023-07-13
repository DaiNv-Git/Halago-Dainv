package com.example.halagodainv.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    Date created;
    @Column(name = "type")
    int type;
    @Column(name = "status")
    int status;
    @Column(name = "title_seo")
    String titleSeo;
    @Column(name = "link_papers")
    String linkPapers;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "newsEntity")
    private List<NewsLanguageEntity> imageBrandMains = new ArrayList<>();
}
