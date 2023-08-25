package com.example.halagodainv.service.impl;

import com.example.halagodainv.dto.solution.livestream.*;
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
            List<ImageSolutionDto> imageSolutionDtos = new ArrayList<>();
            imageSolutionRepository.getAllImage().forEach(i -> {
                ImageSolutionDto imageSolutionDto = new ImageSolutionDto();
                imageSolutionDto.setImage(i.getImage());
                if (language.equals("EN")) {
                    imageSolutionDto.setImageName(i.getImageNameEN());
                } else if (language.equals("VN")) {
                    imageSolutionDto.setImageName(i.getImageNameVN());
                }
                imageSolutionDtos.add(imageSolutionDto);
            });
            map.setImgSlider(imageSolutionDtos);
            return new BaseResponse<>(HttpStatus.OK.value(), HttpStatus.OK.name(), map);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Object getSolutionDetail() {
        try {
            SolutionLiveStreamMapEntity map = solutionLiveStreamRepository.getBySolution();
            List<ImageSolutionDetailDto> imageSolutionDtos = new ArrayList<>();
            imageSolutionRepository.getAllImage().forEach(i -> {
                ImageSolutionDetailDto imageSolutionDto = new ImageSolutionDetailDto();
                imageSolutionDto.setImage(i.getImage());
                imageSolutionDto.setImageNameVN(i.getImageNameEN());
                imageSolutionDto.setImageNameEN(i.getImageNameVN());
                imageSolutionDtos.add(imageSolutionDto);
            });
            SolutionLiveStreamDetailDto solutionLiveStreamDetailDto = new SolutionLiveStreamDetailDto(map,imageSolutionDtos);
            return new BaseResponse<>(HttpStatus.OK.value(), HttpStatus.OK.name(), solutionLiveStreamDetailDto);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Object update(SolutionLiveStreamEdit solutionLiveStreamEdit) {
        try {
            Optional<SolutionLiveStreamEntity> solutionLiveStream = solutionLiveStreamRepository.findById(1L);
            if (solutionLiveStream.isPresent()) {
                solutionLiveStream.get().setLive(solutionLiveStreamEdit.getLive());
                solutionLiveStream.get().setBrand(solutionLiveStreamEdit.getBrand());
                solutionLiveStream.get().setMoney(solutionLiveStreamEdit.getMoney());
                solutionLiveStream.get().setImageSale1(solutionLiveStreamEdit.getImageSale1());
                solutionLiveStream.get().setImageSale2(solutionLiveStreamEdit.getImageSale2());
                solutionLiveStreamRepository.save(solutionLiveStream.get());
            }
            imageSolutionRepository.deleteAll();
            List<ImageLiveStreamEntity> imageLiveStreamEntities = new ArrayList<>();
            for (SolutionLiveStreamImageEdit img : solutionLiveStreamEdit.getImagEdits()) {
                ImageLiveStreamEntity imageLiveStreamEntity = new ImageLiveStreamEntity();
                imageLiveStreamEntity.setImage(img.getImage());
                imageLiveStreamEntity.setSolutionLiveStreamId(1L);
                imageLiveStreamEntity.setImageNameEN(img.getImageNameEN());
                imageLiveStreamEntity.setImageNameVN(img.getImageNameVN());
                imageLiveStreamEntities.add(imageLiveStreamEntity);
            }
            imageSolutionRepository.saveAll(imageLiveStreamEntities);
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
