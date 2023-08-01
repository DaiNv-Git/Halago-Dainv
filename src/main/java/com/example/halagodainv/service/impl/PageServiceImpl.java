package com.example.halagodainv.service.impl;

import com.example.halagodainv.dto.page.PageDto;
import com.example.halagodainv.exception.ErrorResponse;
import com.example.halagodainv.model.IndustryEntity;
import com.example.halagodainv.model.PageEntity;
import com.example.halagodainv.repository.IndustryRepository;
import com.example.halagodainv.repository.PageRepository;
import com.example.halagodainv.request.page.PageAddRequest;
import com.example.halagodainv.request.page.PageEditRequest;
import com.example.halagodainv.request.page.PageSearch;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.response.PageResponse;
import com.example.halagodainv.service.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

@Service
@RequiredArgsConstructor
public class PageServiceImpl implements PageService {
    private final PageRepository pageRepository;
    private final IndustryRepository industryRepository;

    public Object getPageAll(PageSearch pageSearch) {
        try {
            int offset = 0;
            if (pageSearch.getPageNo() > 0) {
                offset = pageSearch.getPageNo() - 1;
            }
            Pageable pageable = PageRequest.of(offset, pageSearch.getPageSize(), Sort.Direction.DESC, "created");
            long totalCount = pageRepository.countByAll(pageSearch.getIndustry(), pageSearch.getExpense(), pageSearch.getFollower(), pageSearch.getPageName());
            List<PageDto> pageDtos = pageRepository.getPages(pageSearch.getIndustry(), pageSearch.getExpense(), pageSearch.getFollower(), pageSearch.getPageName(), pageable);
            if (CollectionUtils.isEmpty(pageDtos)) {
                PageResponse pageResponse = new PageResponse<>(new PageImpl<>(pageDtos, pageable, 0));
                return new BaseResponse<>(HttpStatus.OK.value(), "Lấy dữ liệu thành công", pageResponse);
            }
            PageResponse pageResponse = new PageResponse<>(new PageImpl<>(pageDtos, pageable, totalCount));
            return new BaseResponse<>(HttpStatus.OK.value(), "Lấy dữ liệu thành công", pageResponse);
        } catch (Exception ex) {
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Lấy dữ liệu không thành công", null);
        }
    }

    public Object detail(long pageId) {
        Optional<PageEntity> pageEntity = pageRepository.findById(pageId);
        if (pageEntity.isPresent()) {
            return new BaseResponse<>(HttpStatus.OK.value(), "Lấy dữ liệu chi tiết thành công", pageRepository.getDetailPage(pageId));
        }
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Lấy dữ liệu chi tiết thất bại", null);
    }

    public Object add(PageAddRequest pageAddRequest) {
        try {
            PageEntity pageEntity = new PageEntity();
            pageEntity.setNamePage(pageAddRequest.getPageName());
            pageEntity.setPhone(pageAddRequest.getPhone());
            pageEntity.setLink(pageAddRequest.getLink());
            pageEntity.setFollower(pageAddRequest.getFollower());
            pageEntity.setExpense(pageAddRequest.getExpense());
            List<IndustryEntity> entities = industryRepository.findByIdIn(pageAddRequest.getIndustryId());
            if (!entities.isEmpty()) {
                StringJoiner joiner = new StringJoiner(", ");
                entities.forEach(map -> {
                    joiner.add(map.getIndustryName());
                });
                pageEntity.setIndustry(joiner.toString());
                pageEntity.setIndustryId(InfluencerServiceImpl.parseListIntegerToString(pageAddRequest.getIndustryId()));
            }
            pageEntity.setCreated(new Date());
            pageEntity = pageRepository.save(pageEntity);
            return new BaseResponse<>(HttpStatus.CREATED.value(), "thêm dữ liệu thành công!", pageRepository.getDetailPage(pageEntity.getId()));
        } catch (Exception e) {
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "thêm dữ liệu không thành công!", null);
        }
    }

    public Object edit(PageEditRequest pageEditRequest) {
        try {
            Optional<PageEntity> pageEntity = pageRepository.findById(pageEditRequest.getId());
            if (pageEntity.isPresent()) {
                pageEntity.get().setNamePage(pageEditRequest.getPageName());
                pageEntity.get().setPhone(pageEditRequest.getPhone());
                pageEntity.get().setLink(pageEditRequest.getLink());
                pageEntity.get().setFollower(pageEditRequest.getFollower());
                pageEntity.get().setExpense(pageEditRequest.getExpense());
                List<IndustryEntity> entities = industryRepository.findByIdIn(pageEditRequest.getIndustryId());
                if (!entities.isEmpty()) {
                    StringJoiner joiner = new StringJoiner(", ");
                    entities.forEach(map -> {
                        joiner.add(map.getIndustryName());
                    });
                    pageEntity.get().setIndustryId(InfluencerServiceImpl.parseListIntegerToString(pageEditRequest.getIndustryId()));
                    pageEntity.get().setIndustry(joiner.toString());
                }else {
                    pageEntity.get().setIndustryId("");
                    pageEntity.get().setIndustry("");
                }
                pageRepository.save(pageEntity.get());
                return new BaseResponse<>(HttpStatus.CREATED.value(), "Sửa dữ liệu thành công!", pageRepository.getDetailPage(pageEntity.get().getId()));
            }
            return new ErrorResponse(HttpStatus.FAILED_DEPENDENCY.value(), "Sửa dữ liệu không thành công!", null);
        } catch (Exception e) {
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Sửa dữ liệu không thành công!", null);
        }
    }

    public Object delete(long pageId) {
        try {
            Optional<PageEntity> pageEntity = pageRepository.findById(pageId);
            if (!pageEntity.isPresent()) {
                return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Xóa không thành công", null);
            }
            pageRepository.deleteById(pageId);
            return new BaseResponse<>(HttpStatus.CREATED.value(), "Xóa thành công", null);
        } catch (Exception e) {
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Xóa không thành công", null);
        }
    }
}
