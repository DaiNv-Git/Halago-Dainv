package com.example.halagodainv.service.impl;

import com.example.halagodainv.dto.solution.review.SolutionReview;
import com.example.halagodainv.dto.solution.review.SolutionReviewDetail;
import com.example.halagodainv.dto.solution.review.SolutionReviewDto;
import com.example.halagodainv.dto.solution.review.SolutionReviewMapEntity;
import com.example.halagodainv.model.ImageReviewEntity;
import com.example.halagodainv.model.SolutionReivewLanguageEntity;
import com.example.halagodainv.model.SolutionReviewEntity;
import com.example.halagodainv.repository.ImageSolutionReviewRepository;
import com.example.halagodainv.repository.SolutionReviewLanguageRepository;
import com.example.halagodainv.repository.SolutionReviewRepository;
import com.example.halagodainv.request.solution.review.SolutionReviewEdit;
import com.example.halagodainv.request.solution.review.SolutionReviewEditImage;
import com.example.halagodainv.request.solution.review.SolutionReviewRequestEdit;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.service.SolutionReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SolutionReviewServiceImpl implements SolutionReviewService {
    private final SolutionReviewRepository solutionReviewRepository;
    private final ImageSolutionReviewRepository imageSolutionReviewRepository;
    private final SolutionReviewLanguageRepository solutionReviewLanguageRepository;

    public Object getAll(String language) {
        try {
            List<SolutionReviewMapEntity> mapEntities = solutionReviewRepository.getByAll();
            List<SolutionReviewDto> mapDto = new ArrayList<>();
            mapEntities.forEach(mapEntitiy -> {
                if (language.toUpperCase().equals("VN")) {
                    SolutionReviewDto map = new SolutionReviewDto();
                    map.setTitle(mapEntitiy.getTitle());
                    map.setContent(mapEntitiy.getContent());
                    map.setContentDetail(mapEntitiy.getContentDetail());
                    mapDto.add(map);
                } else {
                    SolutionReviewDto map = new SolutionReviewDto();
                    map.setTitle(mapEntitiy.getTitleEN());
                    map.setContent(mapEntitiy.getContentEN());
                    map.setContentDetail(mapEntitiy.getContentDetail());
                    mapDto.add(map);
                }
            });
            SolutionReview solutionReview = new SolutionReview(mapDto, imageSolutionReviewRepository.getImages());
            return new BaseResponse<>(HttpStatus.OK.value(), HttpStatus.OK.name(), solutionReview);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Object getDetail() {
        try {
            List<SolutionReviewMapEntity> mapEntities = solutionReviewRepository.getByAll();
            SolutionReviewDetail solutionReviewDetail = new SolutionReviewDetail(mapEntities, imageSolutionReviewRepository.getImages());
            return new BaseResponse<>(HttpStatus.OK.value(), HttpStatus.OK.name(), solutionReviewDetail);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }


    public Object updateImageReview(List<SolutionReviewEditImage> images) {
        try {
            imageSolutionReviewRepository.deleteAll();
            List<ImageReviewEntity> entities = new ArrayList<>();
            for (SolutionReviewEditImage image : images) {
                ImageReviewEntity optionalImageReviewEntity = new ImageReviewEntity();
                optionalImageReviewEntity.setImageReview(image.getImage());
                optionalImageReviewEntity.setLink(image.getLink());
                entities.add(optionalImageReviewEntity);
            }
            entities = imageSolutionReviewRepository.saveAll(entities);
            return new BaseResponse<>(HttpStatus.OK.value(), "update image success", entities);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Object updateContent(SolutionReviewRequestEdit edit) {
        try {
            List<SolutionReviewEdit> solutionReviewEdits = edit.getSolutionReview();
            List<SolutionReviewEntity> solutionReviewEntities = new ArrayList<>();
            List<SolutionReivewLanguageEntity> solutionReivewLanguageEntities = new ArrayList<>();
            for (SolutionReviewEdit reviewEdit : solutionReviewEdits) {
                Optional<SolutionReviewEntity> solutionReview = solutionReviewRepository.findById(reviewEdit.getId());
                if (solutionReview.isPresent()) {
                    solutionReview.get().setTitle(reviewEdit.getTitle());
                    solutionReview.get().setContent(reviewEdit.getContent());
                    solutionReview.get().setContentDetail(reviewEdit.getContentDetail());
                    solutionReviewEntities.add(solutionReview.get());
                    Optional<SolutionReivewLanguageEntity> solutionReivewLanguage = solutionReviewLanguageRepository.findBySolutionReviewId(reviewEdit.getId());
                    if (solutionReivewLanguage.isPresent()) {
                        solutionReivewLanguage.get().setTitleEN(reviewEdit.getTitleEN());
                        solutionReivewLanguage.get().setContentEN(reviewEdit.getContentEN());
                        solutionReivewLanguage.get().setContentDetailEN(reviewEdit.getContentDetailEN());
                        solutionReivewLanguageEntities.add(solutionReivewLanguage.get());
                    }
                }
            }
            updateImageReview(edit.getSolutionReviewEditImages());
            solutionReviewRepository.saveAll(solutionReviewEntities);
            solutionReviewLanguageRepository.saveAll(solutionReivewLanguageEntities);
            return new BaseResponse<>(HttpStatus.OK.value(), "update image success", getDetail());
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
