package com.example.halagodainv.dto.news;

import com.example.halagodainv.service.impl.InfluencerServiceImpl;
import lombok.Data;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@Data
public class NewDto {
    private int id;
    private String title;
    private String category;
    private String image;
    private String created;
    private Long topicId;
    private List<Integer> tagId;

    public NewDto(int id, String title, String image, String type, Date created, Long topicId, String tagId) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.category = type;
        this.created = DateFormatUtils.format(created, "dd-MM-yyyy");
        this.topicId = topicId;
        this.tagId = InfluencerServiceImpl.parseStringToListOfIntegers(tagId);
    }
}
