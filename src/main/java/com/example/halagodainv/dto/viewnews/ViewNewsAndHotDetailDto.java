package com.example.halagodainv.dto.viewnews;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewNewsAndHotDetailDto {
    List<ViewTopicDto> viewTopics = new ArrayList<>();
    List<ViewNewsHotDto> viewNews = new ArrayList<>();
    List<ViewNewsHotDto> viewNewHots = new ArrayList<>();
}
