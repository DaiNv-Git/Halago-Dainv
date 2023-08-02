package com.example.halagodainv.service;

import com.example.halagodainv.dto.story.StoryDetailDto;

public interface StoryService {
    Object getStoryHalago(String language);

    Object getStoryHalagoActivities(String language);

    Object detailHalago();

    Object update(StoryDetailDto request);

    Object deleteHalago(long id);

    Object deleteHalagoActivities(long id);
}
