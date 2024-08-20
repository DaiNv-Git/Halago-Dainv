package com.example.halagodainv.service;

import com.example.halagodainv.dto.story.StoryDetailDto;

import java.util.List;

public interface StoryService {

    Object getStoryHalago(String language);

    Object detailHalago();

    Object update(List<StoryDetailDto> request);
}
