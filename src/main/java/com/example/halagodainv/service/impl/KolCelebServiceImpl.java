package com.example.halagodainv.service.impl;

import com.example.halagodainv.dto.kol.BestPickDetailDto;
import com.example.halagodainv.dto.kol.KolCelMapEntity;
import com.example.halagodainv.dto.kol.KolCelebDetailDto;
import com.example.halagodainv.dto.kol.KolCelebDto;
import com.example.halagodainv.model.BestKolImageEntity;
import com.example.halagodainv.model.BookKolsEntity;
import com.example.halagodainv.model.BookKolsLanguageEntity;
import com.example.halagodainv.repository.BestKolImageRepository;
import com.example.halagodainv.repository.BookKolRepository;
import com.example.halagodainv.repository.BookKolsLanguageRepository;
import com.example.halagodainv.request.kolCeleb.KolCelebRequest;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.service.KolCelebService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KolCelebServiceImpl implements KolCelebService {
    private final BookKolRepository bookKolRepository;
    private final BestKolImageRepository bestKolImageRepository;
    private final BookKolsLanguageRepository bookKolsLanguageRepository;

    public Object getKolAll(String language) {
        try {
            KolCelMapEntity map = bookKolRepository.getAll();
            KolCelebDto dto = new KolCelebDto();
            dto.setImageKol1(map.getImageKol1());
            dto.setImageKol2(map.getImageKol2());
            dto.setApproach(map.getApproach());
            dto.setInteract(map.getInteract());
            dto.setRatioInteract(map.getRatioInteract());
            if ("VN".equals(language.toUpperCase())) {
                dto.setTitle(map.getTitle());
                dto.setIntroduce(map.getIntroduce());
                dto.setIntroduceDetail(map.getIntroduceDetail());
            } else {
                dto.setTitle(map.getTitleEn());
                dto.setIntroduce(map.getIntroduceEN());
                dto.setIntroduceDetail(map.getIntroduceDetailEN());
            }
            dto.setBestPickDtos(bestKolImageRepository.getAllImage());
            return new BaseResponse<>(HttpStatus.OK.value(), HttpStatus.OK.name(), dto);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Object getDetailCeleb(Long id, String language) {
        try {
            Optional<BestKolImageEntity> bestCeleb = bestKolImageRepository.findById(id);
            BestPickDetailDto bestPickDetailDto = new BestPickDetailDto();
            if (bestCeleb.isPresent()) {
                bestPickDetailDto.setId(bestCeleb.get().getId());
                bestPickDetailDto.setAge(bestCeleb.get().getAge());
                bestPickDetailDto.setStageName(bestCeleb.get().getStageName());
                if (language.toUpperCase().equals("VN")) {
                    bestPickDetailDto.setName(bestCeleb.get().getName());
                    bestPickDetailDto.setCareer(bestCeleb.get().getCareer());
                    bestPickDetailDto.setDescription(bestCeleb.get().getDescription());
                } else {
                    bestPickDetailDto.setName(bestCeleb.get().getNameEN());
                    bestPickDetailDto.setCareer(bestCeleb.get().getCareerEN());
                    bestPickDetailDto.setDescription(bestCeleb.get().getDescriptionEN());
                }
            }
            return new BaseResponse<>(HttpStatus.OK.value(), HttpStatus.OK.name(), bestPickDetailDto);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Object getDetail() {
        try {
            KolCelMapEntity map = bookKolRepository.getAll();
            KolCelebDetailDto kolCelebDetailDto = new KolCelebDetailDto(map, bestKolImageRepository.findAll());
            return new BaseResponse<>(HttpStatus.OK.value(), HttpStatus.OK.name(), kolCelebDetailDto);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Object update(KolCelebRequest request) {
        try {
            Optional<BookKolsEntity> mapEntity = bookKolRepository.findById(1L);
            if (mapEntity.isPresent()) {
                mapEntity.get().setTitle(request.getTitle());
                mapEntity.get().setImageKol1(request.getImageKol1());
                mapEntity.get().setImageKol2(request.getImageKol2());
                mapEntity.get().setApproach(request.getApproach());
                mapEntity.get().setInteract(request.getInteract());
                mapEntity.get().setRatioInteract(request.getRatioInteract());
                mapEntity.get().setIntroduce(request.getIntroduce());
                mapEntity.get().setIntroduceDetail(request.getIntroduceDetail());
                bookKolRepository.save(mapEntity.get());
                Optional<BookKolsLanguageEntity> mapLanguageEntity = bookKolsLanguageRepository.findById(1L);
                if (mapLanguageEntity.isPresent()) {
                    mapLanguageEntity.get().setTitleEN(request.getTitleEn());
                    mapLanguageEntity.get().setIntroduceEN(request.getIntroduceEN());
                    mapLanguageEntity.get().setIntroduceDetailEN(request.getIntroduceDetailEN());
                    bookKolsLanguageRepository.save(mapLanguageEntity.get());
                }
            }
            bestKolImageRepository.saveAll(request.getKolImageEntityList());
            return new BaseResponse<>(HttpStatus.OK.value(), HttpStatus.OK.name(), getDetail());
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
