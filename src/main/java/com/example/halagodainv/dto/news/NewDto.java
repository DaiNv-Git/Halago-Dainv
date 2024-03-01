package com.example.halagodainv.dto.news;

import com.example.halagodainv.service.impl.InfluencerServiceImpl;
import lombok.Data;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class NewDto {
    private int id;
    private String title;
    private String img;
    private String created;
    private Long topicId;
    private String tagNames;
    private String topicName;
}
