package com.example.halagodainv.dto.viewnews;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewNewsDetailDto {
    private int id;
    private String title;
    private String content;
    private String createdDate;
    private List<Integer> tagId;
    private int topicId;
}
