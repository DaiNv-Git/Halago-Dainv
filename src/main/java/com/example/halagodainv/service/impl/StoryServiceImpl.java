package com.example.halagodainv.service.impl;

import com.example.halagodainv.controller.FileController;
import com.example.halagodainv.dto.solution.livestream.SolutionLiveStreamMapEntity;
import com.example.halagodainv.dto.story.StoryActivitiesDto;
import com.example.halagodainv.dto.story.StoryDetailDto;
import com.example.halagodainv.dto.story.StoryDto;
import com.example.halagodainv.dto.story.StoryMediaDto;
import com.example.halagodainv.model.StoryHalagoEntity;
import com.example.halagodainv.model.StoryTypicalActivitiesEntity;
import com.example.halagodainv.repository.SolutionLiveStreamRepository;
import com.example.halagodainv.repository.StoryHalagoRepository;
import com.example.halagodainv.repository.StoryTypicalActivitiesRepository;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.service.StoryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoryServiceImpl implements StoryService {
    Logger logger = LoggerFactory.getLogger(StoryServiceImpl.class);
    private final StoryHalagoRepository storyHalagoRepository;
    private final StoryTypicalActivitiesRepository storyTypicalActivitiesRepository;
    private final SolutionLiveStreamRepository solutionLiveStreamRepository;


    public Object getStoryHalago(String language) {
        try {
            SolutionLiveStreamMapEntity map = solutionLiveStreamRepository.getBySolution();
            List<StoryHalagoEntity> entities = storyHalagoRepository.findAll();
            List<StoryMediaDto> storyMediaDtos = new ArrayList<>();
            entities.forEach(enMap -> {
                StoryMediaDto storyMediaDto = new StoryMediaDto();
                storyMediaDto.setId(enMap.getId());
                storyMediaDto.setImage(enMap.getImage());
                if (language.toUpperCase().equals("VN")) {
                    storyMediaDto.setTitle(enMap.getTitle());
                    storyMediaDto.setContent(enMap.getContent());
                    storyMediaDto.setContentDetail(enMap.getContentDetail());
                } else {
                    storyMediaDto.setTitle(enMap.getTitleEN());
                    storyMediaDto.setContent(enMap.getContentEN());
                    storyMediaDto.setContentDetail(enMap.getContentDetailEN());
                }
                storyMediaDtos.add(storyMediaDto);
            });
            StoryDto storyDto = new StoryDto(map.getSession(), map.getSatisfiedBrand(), map.getSales(), storyMediaDtos);
            return new BaseResponse<>(HttpStatus.OK.value(), "success", storyDto);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Object getStoryHalagoActivities(String language) {
        try {
            List<StoryTypicalActivitiesEntity> entities = storyTypicalActivitiesRepository.findAll();
            List<StoryActivitiesDto> storyActivitiesDtos = new ArrayList<>();
            entities.forEach(enMap -> {
                StoryActivitiesDto storyMediaDto = new StoryActivitiesDto();
                storyMediaDto.setId(enMap.getId());
                storyMediaDto.setImage(enMap.getImage());
                if (language.toUpperCase().equals("VN")) {
                    storyMediaDto.setTitle(enMap.getTitle());
                    storyMediaDto.setContent(enMap.getContent());
                } else {
                    storyMediaDto.setTitle(enMap.getTitleEN());
                    storyMediaDto.setContent(enMap.getContentEN());
                }
                storyActivitiesDtos.add(storyMediaDto);
            });
            return new BaseResponse<>(HttpStatus.OK.value(), "success", storyActivitiesDtos);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Object detailHalago() {
        try {
            List<StoryHalagoEntity> storyHalagoEntities = storyHalagoRepository.findAll();
            List<StoryTypicalActivitiesEntity> storyTypicalActivitiesEntities = storyTypicalActivitiesRepository.findAll();
            StoryDetailDto storyDetailDto = new StoryDetailDto(storyHalagoEntities, storyTypicalActivitiesEntities);
            return new BaseResponse<>(HttpStatus.OK.value(), "success", storyDetailDto);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Object update(StoryDetailDto request) {
        try {
            storyHalagoRepository.saveAll(request.getStoryHalagoEntities());
            storyTypicalActivitiesRepository.saveAll(request.getTypicalActivitiesEntities());
            return new BaseResponse<>(HttpStatus.OK.value(), "thêm thành công", detailHalago());
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Object deleteHalago(long id) {
        try {
            storyHalagoRepository.deleteById(id);
            return new BaseResponse<>(HttpStatus.OK.value(), "thêm thành công", detailHalago());
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Object deleteHalagoActivities(long id) {
        try {
            storyTypicalActivitiesRepository.deleteById(id);
            return new BaseResponse<>(HttpStatus.OK.value(), "thêm thành công", detailHalago());
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }


}
