package com.example.halagodainv.dto.news;

import com.example.halagodainv.service.impl.InfluencerServiceImpl;
import lombok.Data;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.List;

@Data
public class NewDto {
    private int id;
    private String title;
    private String category;
    private String img;
    private String created;
    private Long topicId;
    private List<Integer> tagId;

    public NewDto(int id, String title, String img, String type, Date created, Long topicId, String tagId) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.category = type;
        this.created = DateFormatUtils.format(created, "dd-MM-yyyy");
        this.topicId = topicId;
        this.tagId = InfluencerServiceImpl.parseStringToListOfIntegers(tagId);
    }
}
