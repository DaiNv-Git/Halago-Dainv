package com.example.halagodainv.service.impl.viewdisplay;

import com.example.halagodainv.dto.brand.BrandGiveDto;
import com.example.halagodainv.model.viewdisplayentity.BrandGiveEntity;
import com.example.halagodainv.repository.viewdisplay.BrandGiveRepository;
import com.example.halagodainv.request.brand.BrandGiveRequest;
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
    public static String BRAND_GIVE = "brand_give";


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

    public Object update(List<BrandGiveRequest> brandGiveEntities) {
        brandGiveRepository.deleteAll();
        List<BrandGiveEntity> giveEntities = new ArrayList<>();
        brandGiveEntities.forEach(i -> {
            String avart =fileImageUtil.uploadImage(i.getAuthorAvatar());
            String logo = fileImageUtil.uploadImage(i.getLogoBrand());
            BrandGiveEntity brandGiveEntity = new BrandGiveEntity();
            brandGiveEntity.setAuthorAvatar(fileImageUtil.uploadImage(avart));
            brandGiveEntity.setLogoBrand(fileImageUtil.uploadImage(logo));
            brandGiveEntity.setPosition(i.getPosition());
            brandGiveEntity.setAuthorName(i.getAuthorName());
            brandGiveEntity.setContent(i.getContent());
            brandGiveEntity.setContentEN(i.getContentEN());
            giveEntities.add(brandGiveEntity);
        });
        return brandGiveRepository.saveAll(giveEntities);
    }
}
