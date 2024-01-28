package com.example.halagodainv.model.viewdisplayentity.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "media_list")
@AllArgsConstructor
@NoArgsConstructor
public class MediaListImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String img;
    private String nameFile;

    public MediaListImg(String img, String nameFile) {
        this.img = img;
        this.nameFile = nameFile;
    }
}
