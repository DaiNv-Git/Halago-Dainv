package com.example.halagodainv.service.impl;

import com.example.halagodainv.dto.kol.*;
import com.example.halagodainv.model.BestKolEntity;
import com.example.halagodainv.model.RepresentativeEntity;
import com.example.halagodainv.repository.BestKolRepository;
import com.example.halagodainv.repository.RepresentativesRepository;
import com.example.halagodainv.request.kolCeleb.KolCelebRequest;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.service.KolCelebService;
import com.example.halagodainv.until.FileImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KolCelebServiceImpl implements KolCelebService {
    private final RepresentativesRepository representativesRepository;
    private final BestKolRepository bestKolRepository;
    private final FileImageUtil fileImageUtil;
    private static String KOL_UPLOAD= "kol";

    public Object getKolAll(String language) {
        try {
            List<RepresentativeMapEntity> maps = representativesRepository.getAll();
            List<RepresentativeDtos> representativeDtos = new ArrayList<>();
            for (RepresentativeMapEntity map : maps) {
                RepresentativeDtos dto = new RepresentativeDtos();
                dto.setImg(map.getImg());
                dto.setImg2(map.getImg2());
                dto.setInteractions(map.getInteractions());
                dto.setInteractionRate(map.getInteractionRate());
                dto.setReach(map.getReach());
                if ("VN".equals(language.toUpperCase())) {
                    dto.setName(map.getName());
                    dto.setContent(map.getContent());
                } else {
                    dto.setName(map.getNameEN());
                    dto.setContent(map.getContentEN());
                }
                representativeDtos.add(dto);
            }
            List<KolMapEntity> kolMapEntities = bestKolRepository.getAllImage();
            List<KolDetailDto> mapsDto = new ArrayList<>();
            for (KolMapEntity bestMap : kolMapEntities) {
                KolDetailDto bestPickDto = new KolDetailDto();
                bestPickDto.setImage(bestMap.getImg());
                if ("VN".equals(language.toUpperCase())) {
                    bestPickDto.setName(bestMap.getName());
                    bestPickDto.setJob(bestMap.getJob());
                } else {
                    bestPickDto.setName(bestMap.getNameEN());
                    bestPickDto.setJob(bestMap.getJobEN());
                }
                mapsDto.add(bestPickDto);
            }
            return new BaseResponse<>(HttpStatus.OK.value(), HttpStatus.OK.name(), new KolAndRepresenDto(mapsDto, representativeDtos));
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Object getDetailCeleb(Long id, String language) {
        return null;
    }

    public Object getDetail() {
        try {
            List<RepresentativeMapEntity> representatives = representativesRepository.getAll();
            List<KolMapEntity> kols = bestKolRepository.getAllImage();
            KolAndRepresentDetailDto detailDto = new KolAndRepresentDetailDto(representatives, kols);
            return new BaseResponse<>(HttpStatus.OK.value(), HttpStatus.OK.name(), detailDto);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Object update(KolCelebRequest request) {
        try {
            representativesRepository.deleteAll();
            bestKolRepository.deleteAll();
            List<RepresentativeEntity> representativeEntities = new ArrayList<>();
            for (RepresentativeMapEntity representativeMap : request.getRepresentative()) {
                RepresentativeEntity representativeEntity = new RepresentativeEntity();
                representativeEntity.setImg(fileImageUtil.uploadImage(KOL_UPLOAD,representativeMap.getImg()));
                representativeEntity.setImg2(fileImageUtil.uploadImage(KOL_UPLOAD,representativeMap.getImg2()));
                representativeEntity.setName(representativeMap.getName());
                representativeEntity.setNameEN(representativeMap.getNameEN());
                representativeEntity.setContent(representativeMap.getContent());
                representativeEntity.setContentEN(representativeMap.getContentEN());
                representativeEntity.setInteractions(representativeMap.getInteractions());
                representativeEntity.setInteractionRate(representativeMap.getInteractionRate());
                representativeEntity.setReach(representativeMap.getReach());
                representativeEntities.add(representativeEntity);
            }

            List<BestKolEntity> bestKolEntities = new ArrayList<>();
            for (KolMapEntity kolMapEntity : request.getKol()) {
                BestKolEntity bestKolEntity = new BestKolEntity();
                bestKolEntity.setImage(fileImageUtil.uploadImage(KOL_UPLOAD,kolMapEntity.getImg()));
                bestKolEntity.setName(kolMapEntity.getName());
                bestKolEntity.setNameEN(kolMapEntity.getNameEN());
                bestKolEntity.setJob(kolMapEntity.getJob());
                bestKolEntity.setJobEN(kolMapEntity.getJobEN());
                bestKolEntities.add(bestKolEntity);
            }
            representativesRepository.saveAll(representativeEntities);
            bestKolRepository.saveAll(bestKolEntities);
            return new BaseResponse<>(HttpStatus.OK.value(), HttpStatus.OK.name(), getDetail());
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
