package com.example.halagodainv.dto.viewnews;

import com.example.halagodainv.service.impl.InfluencerServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewNewsMap {
    private int id;
    private String title;
    private String content;
    private String createdDate;
    private int topicId;
    private List<Integer> tagId;
    private String img;

    public ViewNewsMap(int id, String title, String content, Date createdDate, Long topicId, String tagId, String img) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdDate = DateFormatUtils.format(createdDate, "yyyy-MM-dd");
        this.topicId = Integer.parseInt(String.valueOf(topicId));
        this.tagId = InfluencerServiceImpl.parseStringToListOfIntegers(tagId);
        this.img = img;
    }
}
