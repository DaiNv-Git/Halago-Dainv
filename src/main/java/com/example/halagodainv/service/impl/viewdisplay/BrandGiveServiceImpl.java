package com.example.halagodainv.service.impl.viewdisplay;

import com.example.halagodainv.dto.brand.BrandGiveDto;
import com.example.halagodainv.model.viewdisplayentity.BrandGiveEntity;
import com.example.halagodainv.repository.viewdisplay.BrandGiveRepository;
import com.example.halagodainv.service.BrandGiveService;
import com.example.halagodainv.until.FileImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandGiveServiceImpl implements BrandGiveService {
    private final BrandGiveRepository brandGiveRepository;

    private final FileImageUtil fileImageUtil;


    public Object getAll(String language) {
        List<BrandGiveEntity> brandGiveEntities = brandGiveRepository.findAll();
        List<BrandGiveDto> brandGiveDtos = new ArrayList<>();
        brandGiveEntities.forEach(map -> {
            BrandGiveDto brandGiveDto = new BrandGiveDto();
            brandGiveDto.setLogoBrand(map.getLogoBrand());
            brandGiveDto.setAuthorAvatar(map.getAuthorAvatar());
            brandGiveDto.setAuthorName(map.getAuthorName());
            brandGiveDto.setPosition(map.getPosition());
            if (language.equalsIgnoreCase("VN")) {
                brandGiveDto.setContent(map.getContent());
            } else if (language.equalsIgnoreCase("EN")) {
                brandGiveDto.setContent(map.getContentEN());
            }
            brandGiveDtos.add(brandGiveDto);
        });
        return brandGiveDtos;
    }

    public Object getDetail() {
        return brandGiveRepository.findAll();
    }

    public Object update(List<BrandGiveEntity> brandGiveEntities) {
        brandGiveRepository.deleteAll();
        brandGiveEntities.forEach(i -> {
            BrandGiveEntity brandGiveEntity = new BrandGiveEntity();
            brandGiveEntity.setAuthorAvatar(i.getAuthorAvatar());
            brandGiveEntity.setLogoBrand(i.getLogoBrand());
        });
        return brandGiveRepository.saveAll(brandGiveEntities);
    }
}
