package com.example.halagodainv.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "solution_live_stream_language")
public class SolutionLiveStreamLanguageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "solution_id")
    private long solutionId;
    @Column(name = "content_one_en")
    private String contentOneEN;
    @Column(name = "content_two_en")
    private String contentTwoEN;
    @Column(name = "content_three_en ")
    private String contentThreeEN;
}
