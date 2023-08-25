package com.example.halagodainv.service.impl;

import com.example.halagodainv.dto.solution.review.*;
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
                    map.setImg(mapEntitiy.getImg());
                    map.setTitle(mapEntitiy.getTitle());
                    map.setContent(mapEntitiy.getContent());
                    map.setContentDetail(mapEntitiy.getContentDetail());
                    mapDto.add(map);
                } else {
                    SolutionReviewDto map = new SolutionReviewDto();
                    map.setImg(mapEntitiy.getImg());
                    map.setTitle(mapEntitiy.getTitleEN());
                    map.setContent(mapEntitiy.getContentEN());
                    map.setContentDetail(mapEntitiy.getContentDetail());
                    mapDto.add(map);
                }
            });
            List<ImageReviewAllDto> list = new ArrayList<>();
            imageSolutionReviewRepository.getImages().forEach(i -> {
                ImageReviewAllDto imageReviewAllDto = new ImageReviewAllDto();
                imageReviewAllDto.setImg(i.getImage());
                imageReviewAllDto.setLink(i.getLink());
                if (language.toUpperCase().equals("VN")) {
                    imageReviewAllDto.setName(i.getName());
                } else {
                    imageReviewAllDto.setName(i.getNameEN());
                }
                list.add(imageReviewAllDto);
            });
            SolutionReview solutionReview = new SolutionReview(mapDto, list);
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
                optionalImageReviewEntity.setNameVN(image.getName());
                optionalImageReviewEntity.setNameEN(image.getNameEN());
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
            solutionReviewRepository.deleteAll();
            solutionReviewLanguageRepository.deleteAll();
            for (SolutionReviewEdit reviewEdit : solutionReviewEdits) {
                SolutionReviewEntity solutionReview = new SolutionReviewEntity();
                solutionReview.setTitle(reviewEdit.getTitle());
                solutionReview.setContent(reviewEdit.getContent());
                solutionReview.setContentDetail(reviewEdit.getContentDetail());
                solutionReview = solutionReviewRepository.save(solutionReview);

                SolutionReivewLanguageEntity solutionReivewLanguage = new SolutionReivewLanguageEntity();
                solutionReivewLanguage.setTitleEN(reviewEdit.getTitleEN());
                solutionReivewLanguage.setContentEN(reviewEdit.getContentEN());
                solutionReivewLanguage.setContentDetailEN(reviewEdit.getContentDetailEN());
                solutionReivewLanguage.setSolutionReviewId(solutionReview.getId());
                solutionReivewLanguage = solutionReviewLanguageRepository.save(solutionReivewLanguage);
            }
            updateImageReview(edit.getSolutionReviewEditImages());
            return new BaseResponse<>(HttpStatus.OK.value(), "update image success", getDetail());
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
