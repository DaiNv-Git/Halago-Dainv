package com.example.halagodainv.service.impl.viewdisplay;

import com.example.halagodainv.dto.brand.ViewBrandDto;
import com.example.halagodainv.model.viewdisplayentity.ViewBrandEntity;
import com.example.halagodainv.repository.viewdisplay.ViewBrandRepository;
import com.example.halagodainv.request.brand.ViewBrandRequest;
import com.example.halagodainv.response.PageResponse;
import com.example.halagodainv.service.ViewBrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ViewBrandServiceImpl implements ViewBrandService {

    private final ViewBrandRepository viewBrandRepository;


    public List<ViewBrandDto> getBranViews(String language) {
        List<ViewBrandEntity> viewBrandEntities = viewBrandRepository.findAll();
        List<ViewBrandDto> viewBrandDtos = new ArrayList<>();
        viewBrandEntities.forEach(map -> {
            ViewBrandDto viewBrandDto = new ViewBrandDto();
            viewBrandDto.setId(map.getId());
            viewBrandDto.setName(map.getName());
            viewBrandDto.setLogoBrand(map.getLogoBrand());
            viewBrandDto.setPositionId(map.getPositionId());
            if (language.equals("VN")) {
                viewBrandDto.setDescription(map.getDescriptionVN());
            } else if (language.equals("EN")) {
                viewBrandDto.setDescription(map.getDescriptionEN());
            }
            viewBrandDtos.add(viewBrandDto);
        });
        return viewBrandDtos;
    }

    public PageResponse<?> getAll(int pageNo, int pageSize) {
        int offset = pageNo > 0 ? pageNo - 1 : 0;
        PageResponse<?> pageResponse;
        Pageable pageable = PageRequest.of(offset, pageSize, Sort.Direction.DESC, "id");
        List<ViewBrandEntity> viewBrandEntities = viewBrandRepository.findAllBy(pageable);
        if (CollectionUtils.isEmpty(viewBrandEntities)) {
            pageResponse = new PageResponse<>(new PageImpl<>(viewBrandEntities, pageable, 0));
            return pageResponse;
        }
        pageResponse = new PageResponse<>(new PageImpl<>(viewBrandEntities, pageable, viewBrandEntities.size()));
        return pageResponse;
    }

    public ViewBrandEntity getDetail(Long id) {
        Optional<ViewBrandEntity> viewBrandEntity = viewBrandRepository.findById(id);
        if (viewBrandEntity.isPresent()) {
            return viewBrandEntity.get();
        }
        throw new RuntimeException("data is not exits!");
    }


    public ViewBrandEntity add(ViewBrandRequest viewBrandRequest) {
        Optional<ViewBrandEntity> checkView = viewBrandRepository.findById(viewBrandRequest.getId());
        if (checkView.isPresent()) {
            throw new RuntimeException("data is exits!");
        }

        ViewBrandEntity viewBrand = new ViewBrandEntity();
        viewBrand.setLogoBrand(viewBrandRequest.getLogoBrand());
        viewBrand.setName(viewBrandRequest.getName());
        viewBrand.setPositionId(viewBrandRequest.getPositionId());
        viewBrand.setDescriptionVN(viewBrandRequest.getDescriptionVN());
        viewBrand.setDescriptionEN(viewBrandRequest.getDescriptionEN());
        viewBrand = viewBrandRepository.save(viewBrand);
        return viewBrand;
    }

    public ViewBrandEntity edit(ViewBrandRequest viewBrandRequest) {
        Optional<ViewBrandEntity> checkView = viewBrandRepository.findById(viewBrandRequest.getId());
        if (checkView.isPresent()) {
            throw new RuntimeException("data is not exits!");
        }
        checkView.get().setLogoBrand(viewBrandRequest.getLogoBrand());
        checkView.get().setName(viewBrandRequest.getName());
        checkView.get().setPositionId(viewBrandRequest.getPositionId());
        checkView.get().setDescriptionVN(viewBrandRequest.getDescriptionVN());
        checkView.get().setDescriptionEN(viewBrandRequest.getDescriptionEN());
        viewBrandRepository.save(checkView.get());
        return checkView.get();
    }

    public void delete(Long viewId) {
        viewBrandRepository.deleteById(viewId);
    }

}
