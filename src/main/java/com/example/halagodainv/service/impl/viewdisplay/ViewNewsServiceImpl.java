package com.example.halagodainv.service.impl.viewdisplay;

import com.example.halagodainv.dto.viewnews.ViewNewsDetailDto;
import com.example.halagodainv.dto.viewnews.ViewNewsDto;
import com.example.halagodainv.dto.viewnews.ViewNewsMap;
import com.example.halagodainv.repository.viewdisplay.ViewNewsRepository;
import com.example.halagodainv.response.PageResponse;
import com.example.halagodainv.service.ViewNewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ViewNewsServiceImpl implements ViewNewsService {
    private final ViewNewsRepository viewNewsRepository;

    public PageResponse<?> getViewNews(int pageNo, int pageSize, String language) {
        PageResponse<?> pageResponse;
        int offset = pageNo > 0 ? pageNo - 1 : 0;
        Pageable pageable = PageRequest.of(offset, pageSize, Sort.Direction.DESC, "id");
        List<ViewNewsMap> viewNewsMaps = viewNewsRepository.getALl();
        List<ViewNewsDto> viewNewsDtos = new ArrayList<>();

        if (CollectionUtils.isEmpty(viewNewsMaps)) {
            pageResponse = new PageResponse<>(new PageImpl<>(viewNewsDtos, pageable, 0));
            return pageResponse;
        }
        for (ViewNewsMap map : viewNewsMaps) {
            ViewNewsDto viewNewsDto = new ViewNewsDto();
            viewNewsDto.setId(map.getId());
            viewNewsDto.setImage1(map.getImage1());
            viewNewsDto.setImage2(map.getImage2());
            viewNewsDto.setCreatedDate(map.getCreatedDate());
            if (language.equals("VN")) {
                viewNewsDto.setTitle(map.getTitle());
                viewNewsDto.setHerder(map.getHerder());
            } else if (language.equals("EN")) {
                viewNewsDto.setTitle(map.getTitleEN());
                viewNewsDto.setHerder(map.getHerderEN());
            }
            viewNewsDtos.add(viewNewsDto);
        }
        int pageEnd = Math.min((offset * pageSize) + pageSize, viewNewsDtos.size());
        pageResponse = new PageResponse<>(new PageImpl<>(viewNewsDtos.subList((offset * pageSize), pageEnd), pageable, viewNewsDtos.size()));
        return pageResponse;
    }

    public ViewNewsDetailDto getViewNewsDetail(int id, String language) {
        ViewNewsMap viewNewsMaps = viewNewsRepository.findByViewNewId(id);
        ViewNewsDetailDto viewNewsDetailDto = new ViewNewsDetailDto();
        viewNewsDetailDto.setId(viewNewsMaps.getId());
        viewNewsDetailDto.setImage1(viewNewsMaps.getImage1());
        viewNewsDetailDto.setImage2(viewNewsMaps.getImage2());
        viewNewsDetailDto.setCreatedDate(viewNewsMaps.getCreatedDate());
        if (language.equals("VN")) {
            viewNewsDetailDto.setTitle(viewNewsMaps.getTitle());
            viewNewsDetailDto.setHerder(viewNewsMaps.getHerder());
            viewNewsDetailDto.setBody(viewNewsMaps.getBody());
            viewNewsDetailDto.setFooter(viewNewsMaps.getFooter());
        } else if (language.equals("EN")) {
            viewNewsDetailDto.setTitle(viewNewsMaps.getTitleEN());
            viewNewsDetailDto.setHerder(viewNewsMaps.getHerderEN());
            viewNewsDetailDto.setBody(viewNewsMaps.getBody());
            viewNewsDetailDto.setFooter(viewNewsMaps.getFooter());
        }
        return viewNewsDetailDto;
    }

}
