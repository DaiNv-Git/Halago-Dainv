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
    private String category;
    private String img;
    private String created;
    private Long topicId;
    private List<Integer> tagId = new ArrayList<>();
    private String tagNames;
    private String topicName;

    public NewDto(int id, String title, String img, String type, Date created, Long topicId, String tagId, String tagName) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.category = type;
        this.created = DateFormatUtils.format(created, "yyyy-MM-dd");
        this.topicId = topicId;
        this.tagId = InfluencerServiceImpl.parseStringToListOfIntegers(tagId);
        this.tagNames = tagName;
        this.topicName = choseTagName(topicId);
    }

    private static String choseTagName(Long topicId) {
        if (topicId == 1L) {
            return "Các dự án đã triển khai";
        } else if (topicId == 2L) {
            return "Dự án hợp tác cùng KOL,Celeb";
        } else if (topicId == 3L) {
            return "Tin tức HOT về Influencer KOL";
        } else if (topicId == 4L) {
            return "Cập nhật tin tức về thị trường Influencer marketing";
        } else if (topicId == 5L) {
            return "Phương pháp tối ưu hiệu quả khi triển khai Influencer marketing";
        } else if (topicId == 6L) {
            return "Case study cùng nhãn hàng";
        } else if (topicId == 7L) {
            return "Lưu ý dành riêng cho các bạn";
        } else if (topicId == 8L) {
            return "Halago - Hoạt động tiêu biểu";
        }
        return "";
    }
}
