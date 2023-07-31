package com.example.halagodainv.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "solution_live_stream_language")
public class SolutionLiveStreamLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "content_oneEN")
    private String contentOneEN;
    @Column(name = "content_twoEN")
    private String contentTwoEN;
    @Column(name = "content_threeEN")
    private String contentThreeEN;
}
