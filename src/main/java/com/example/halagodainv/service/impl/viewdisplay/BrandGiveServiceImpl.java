package com.example.halagodainv.service.impl.viewdisplay;

import com.example.halagodainv.dto.brand.BrandGiveDto;
import com.example.halagodainv.model.viewdisplayentity.BrandGiveEntity;
import com.example.halagodainv.repository.viewdisplay.BrandGiveRepository;
import com.example.halagodainv.service.BrandGiveService;
import com.example.halagodainv.until.FileImageUntil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandGiveServiceImpl implements BrandGiveService {
    private final BrandGiveRepository brandGiveRepository;

    private final FileImageUntil fileImageUntil;


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
//        brandGiveEntities.forEach(i->{
//            BrandGiveEntity brandGiveEntity = new BrandGiveEntity();
//            fileImageUntil.uploadImage(i.getAuthorAvatar())
//        });
        return brandGiveRepository.saveAll(brandGiveEntities);
    }
}
