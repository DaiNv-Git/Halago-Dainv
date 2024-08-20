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

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoryServiceImpl implements StoryService {
    Logger logger = LoggerFactory.getLogger(StoryServiceImpl.class);
    private final StoryHalagoRepository storyHalagoRepository;
    private final SolutionLiveStreamRepository solutionLiveStreamRepository;
    private final FileImageUtil fileImageUtil;
    public static String STORY = "story";


    public Object getStoryHalago(String language) {
        try {
            SolutionLiveStreamMapEntity map = solutionLiveStreamRepository.getBySolution();
            List<StoryHalagoEntity> entities = storyHalagoRepository.findAll();
            List<StoryMediaDto> storyMediaDtos = new ArrayList<>();
            entities.forEach(str ->
            {
                StoryMediaDto storyMediaDto = new StoryMediaDto();
                storyMediaDto.setImage(str.getImg());
                if (language.equalsIgnoreCase("VN")) {
                    storyMediaDto.setContent(str.getContent());
                } else if (language.equalsIgnoreCase("EN")) {
                    storyMediaDto.setContent(str.getContentEN());
                }
                storyMediaDtos.add(storyMediaDto);
            });

            StoryDto storyDto = new StoryDto(map.getLive(), map.getBrand(), map.getMoney(), storyMediaDtos);
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


    @Transactional
    public Object update(List<StoryDetailDto> request) {
        try {
            if (request.isEmpty()) {
                return new BaseResponse<>(HttpStatus.OK.value(), "No data is changed", "");
            }

            List<Long> ids = new ArrayList<>();
            request.forEach(r -> {
                if (r.isDelete()) {
                    Optional<StoryHalagoEntity> entity = storyHalagoRepository.findById(r.getId());
                    if (entity.isPresent()) {
                        ids.add(r.getId());
                    }
                }
            });
            storyHalagoRepository.deleteByIds(ids);

            List<StoryHalagoEntity> entities = new ArrayList<>();
            request.forEach(r -> {
                Optional<StoryHalagoEntity> entity = storyHalagoRepository.findById(r.getId());
                if (entity.isPresent()) {
                    entity.get().setContent(r.getContent());
                    entity.get().setContentEN(r.getContentEN());
                    entity.get().setImg(fileImageUtil.uploadImage(r.getImg()));
                    entities.add(entity.get());
                } else {
                    if (!r.isDelete() || r.getId() == 0) {
                        StoryHalagoEntity setData = new StoryHalagoEntity();
                        setData.setContent(r.getContent());
                        setData.setContentEN(r.getContentEN());
                        setData.setImg(fileImageUtil.uploadImage(r.getImg()));
                        entities.add(setData);
                    }
                }

            });
            List<StoryHalagoEntity> getDatas = storyHalagoRepository.saveAllAndFlush(entities);
            return new BaseResponse<>(HttpStatus.OK.value(), "add or update success", getDatas);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

}
