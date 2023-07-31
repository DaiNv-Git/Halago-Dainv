package com.example.halagodainv.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "solution_review_language")
public class SolutionReivewLanguageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title_en")
    private String titleEN;
    @Column(name = "content_en")
    private String contentEN;
    @Column(name = "content_detail_en")
    private String contentDetailEN;
    @Column(name = "solution_review_id")
    private long solutionReviewId;
}
