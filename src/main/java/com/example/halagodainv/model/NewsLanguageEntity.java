package com.example.halagodainv.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "news_language")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewsLanguageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lang")
    int idLang;
    @Column(name = "title")
    String title;
    @Column(name = "description")
    String description;
    @Column(name = "content")
    String content;
    @Column(name = "language")
    String language;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "new_id")
    private NewsEntity newsEntity;
}
