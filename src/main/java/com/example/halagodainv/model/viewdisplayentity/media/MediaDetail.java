package com.example.halagodainv.model.viewdisplayentity.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "MediaDetail")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "image")
    private String image;
    @Column(name = "type")
    private int type;
    @Column(name = "is_show")
    private boolean isShow;
}
