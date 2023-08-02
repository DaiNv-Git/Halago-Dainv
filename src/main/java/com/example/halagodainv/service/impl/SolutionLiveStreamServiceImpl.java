package com.example.halagodainv.service.impl;

import com.example.halagodainv.dto.solution.livestream.SolutionLiveStreamDTO;
import com.example.halagodainv.dto.solution.livestream.SolutionLiveStreamDetailDto;
import com.example.halagodainv.dto.solution.livestream.SolutionLiveStreamMapEntity;
import com.example.halagodainv.model.ImageLiveStreamEntity;
import com.example.halagodainv.model.SolutionLiveStreamEntity;
import com.example.halagodainv.model.SolutionLiveStreamLanguageEntity;
import com.example.halagodainv.repository.ImageSolutionRepository;
import com.example.halagodainv.repository.SolutionLiveStreamLanguageRepository;
import com.example.halagodainv.repository.SolutionLiveStreamRepository;
import com.example.halagodainv.request.solution.livestream.SolutionLiveStreamImageEdit;
import com.example.halagodainv.request.solution.livestream.SolutionLiveStreamEdit;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.service.SolutionLiveStreamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SolutionLiveStreamServiceImpl implements SolutionLiveStreamService {
    private final SolutionLiveStreamRepository solutionLiveStreamRepository;
    private final SolutionLiveStreamLanguageRepository solutionLiveStreamLanguageRepository;
    private final ImageSolutionRepository imageSolutionRepository;

    public Object getSolution(String language) {
        try {
            SolutionLiveStreamMapEntity map = solutionLiveStreamRepository.getBySolution();
            SolutionLiveStreamDTO mapDto = new SolutionLiveStreamDTO();
            mapDto.setSales(map.getSales());
            mapDto.setSession(map.getSession());
            mapDto.setSatisfiedBrand(map.getSatisfiedBrand());
            mapDto.setImageSale1(map.getImageSale1());
            mapDto.setImageSale2(map.getImageSale2());
            if (language.toUpperCase().equals("VN")) {
                mapDto.setContentOne(map.getContentOne());
                mapDto.setContentThree(map.getContentThree());
                mapDto.setContentTwo(map.getContentTwo());
            } else {
                mapDto.setContentOne(map.getContentOneEN());
                mapDto.setContentThree(map.getContentThreeEN());
                mapDto.setContentTwo(map.getContentTwoEN());
            }
            mapDto.setSolutionDtos(imageSolutionRepository.getAllImage());
            return new BaseResponse<>(HttpStatus.OK.value(), HttpStatus.OK.name(), mapDto);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Object getSolutionDetail() {
        try {
            SolutionLiveStreamMapEntity map = solutionLiveStreamRepository.getBySolution();
            SolutionLiveStreamDetailDto solutionLiveStreamDetailDto = new SolutionLiveStreamDetailDto(map, imageSolutionRepository.getAllImage());
            return new BaseResponse<>(HttpStatus.OK.value(), HttpStatus.OK.name(), solutionLiveStreamDetailDto);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Object update(SolutionLiveStreamEdit solutionLiveStreamEdit) {
        try {
            Optional<SolutionLiveStreamEntity> solutionLiveStream = solutionLiveStreamRepository.findById(1L);
            if (solutionLiveStream.isPresent()) {
                solutionLiveStream.get().setSales(solutionLiveStreamEdit.getSales());
                solutionLiveStream.get().setSession(solutionLiveStreamEdit.getSession());
                solutionLiveStream.get().setSatisfiedBrand(solutionLiveStreamEdit.getSatisfiedBrand());
                solutionLiveStream.get().setContentOne(solutionLiveStreamEdit.getContentOne());
                solutionLiveStream.get().setContentTwo(solutionLiveStreamEdit.getContentTwo());
                solutionLiveStream.get().setContentThree(solutionLiveStreamEdit.getContentThree());
                solutionLiveStreamRepository.save(solutionLiveStream.get());

                Optional<SolutionLiveStreamLanguageEntity> solutionLiveStreamLanguage = solutionLiveStreamLanguageRepository.findById(solutionLiveStream.get().getId());
                if (solutionLiveStreamLanguage.isPresent()) {
                    solutionLiveStreamLanguage.get().setContentOneEN(solutionLiveStreamEdit.getContentOneEN());
                    solutionLiveStreamLanguage.get().setContentTwoEN(solutionLiveStreamEdit.getContentTwoEN());
                    solutionLiveStreamLanguage.get().setContentThreeEN(solutionLiveStreamEdit.getContentThreeEN());
                    solutionLiveStreamLanguageRepository.save(solutionLiveStreamLanguage.get());
                }
                imageSolutionRepository.deleteAll();
                List<ImageLiveStreamEntity> imageLiveStreamEntities = new ArrayList<>();
                for (SolutionLiveStreamImageEdit img:solutionLiveStreamEdit.getImagEdits()){
                    ImageLiveStreamEntity imageLiveStreamEntity = new ImageLiveStreamEntity();
                    imageLiveStreamEntity.setImage(img.getImage());
                    imageLiveStreamEntity.setSolutionLiveStreamId(1L);
                    imageLiveStreamEntities.add(imageLiveStreamEntity);
                }
                imageSolutionRepository.saveAll(imageLiveStreamEntities);
            }
            return new BaseResponse<>(HttpStatus.OK.value(), HttpStatus.OK.name(), getSolutionDetail());
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Object addImage(List<SolutionLiveStreamImageEdit> edits) {
        List<ImageLiveStreamEntity> imageLiveStreamEntities = new ArrayList<>();
        try {
            edits.forEach(img -> {
                ImageLiveStreamEntity imageLiveStreamEntity = new ImageLiveStreamEntity();
                imageLiveStreamEntity.setImage(img.getImage());
                imageLiveStreamEntity.setSolutionLiveStreamId(1L);
                imageLiveStreamEntities.add(imageLiveStreamEntity);
            });
            imageSolutionRepository.saveAll(imageLiveStreamEntities);
            return new BaseResponse<>(HttpStatus.OK.value(), HttpStatus.OK.name(), imageSolutionRepository.getAllImage());
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void deleteImage(long imageId) {
        imageSolutionRepository.deleteById(imageId);
    }
}
