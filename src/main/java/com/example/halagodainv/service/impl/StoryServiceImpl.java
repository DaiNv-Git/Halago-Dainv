package com.example.halagodainv.service.impl;

import com.example.halagodainv.dto.solution.livestream.SolutionLiveStreamMapEntity;
import com.example.halagodainv.dto.story.StoryDetailDto;
import com.example.halagodainv.dto.story.StoryDto;
import com.example.halagodainv.dto.story.StoryMediaDto;
import com.example.halagodainv.model.StoryHalagoEntity;
import com.example.halagodainv.repository.SolutionLiveStreamRepository;
import com.example.halagodainv.repository.StoryHalagoRepository;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.service.StoryService;
import com.example.halagodainv.until.FileImageUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoryServiceImpl implements StoryService {
    Logger logger = LoggerFactory.getLogger(StoryServiceImpl.class);
    private final StoryHalagoRepository storyHalagoRepository;
    private final SolutionLiveStreamRepository solutionLiveStreamRepository;
    private final FileImageUtil fileImageUtil;


    public Object getStoryHalago(String language) {
        try {
            SolutionLiveStreamMapEntity map = solutionLiveStreamRepository.getBySolution();
            Optional<StoryHalagoEntity> entities = storyHalagoRepository.findById(2L);
            StoryMediaDto storyMediaDto = new StoryMediaDto();
            storyMediaDto.setImage(entities.get().getImg());
            if (language.toUpperCase().equals("VN")) {
                storyMediaDto.setContent(entities.get().getContent());
            } else {
                storyMediaDto.setContent(entities.get().getContentEN());
            }
            StoryDto storyDto = new StoryDto(map.getLive(), map.getBrand(), map.getMoney(), storyMediaDto);
            return new BaseResponse<>(HttpStatus.OK.value(), "success", storyDto);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Object detailHalago() {
        try {
            List<StoryHalagoEntity> storyHalagoEntities = storyHalagoRepository.findAll();
            return new BaseResponse<>(HttpStatus.OK.value(), "success", storyHalagoEntities);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Object update(StoryDetailDto request) {
        try {
            Optional<StoryHalagoEntity> entities = storyHalagoRepository.findById(2L);
            entities.get().setContent(request.getContent());
            entities.get().setContentEN(request.getContentEN());
            entities.get().setImg(request.getImg());
            storyHalagoRepository.save(entities.get());
            return new BaseResponse<>(HttpStatus.OK.value(), "thêm thành công", entities);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

}
