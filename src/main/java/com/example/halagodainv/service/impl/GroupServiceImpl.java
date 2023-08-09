package com.example.halagodainv.service.impl;

import com.example.halagodainv.dto.group.GroupDto;
import com.example.halagodainv.exception.ErrorResponse;
import com.example.halagodainv.model.GroupEntity;
import com.example.halagodainv.model.IndustryEntity;
import com.example.halagodainv.repository.GroupRepository;
import com.example.halagodainv.repository.IndustryRepository;
import com.example.halagodainv.request.group.GroupAddRequest;
import com.example.halagodainv.request.group.GroupEditRequest;
import com.example.halagodainv.request.group.GroupSearch;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.response.PageResponse;
import com.example.halagodainv.service.GroupService;
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
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final IndustryRepository industryRepository;

    public Object getGroupAll(GroupSearch groupSearch) {
        try {
            int offset = 0;
            if (groupSearch.getPageNo() > 0) {
                offset = groupSearch.getPageNo() - 1;
            }
            Pageable pageable = PageRequest.of(offset, groupSearch.getPageSize(), Sort.Direction.DESC, "id");
            long totalCount = groupRepository.countByAll(groupSearch.getIndustry(), groupSearch.getExpense(), groupSearch.getMemberTotal(), groupSearch.getGroupName());
            List<GroupDto> groupDtos = groupRepository.getGroups(groupSearch.getIndustry(), groupSearch.getExpense(), groupSearch.getMemberTotal(), groupSearch.getGroupName(), pageable);
            if (CollectionUtils.isEmpty(groupDtos)) {
                PageResponse pageResponse = new PageResponse<>(new PageImpl<>(groupDtos, pageable, 0));
                return new BaseResponse<>(HttpStatus.OK.value(), "Lấy dữ liệu thành công", pageResponse);
            }
            PageResponse pageResponse = new PageResponse<>(new PageImpl<>(groupDtos, pageable, totalCount));
            return new BaseResponse<>(HttpStatus.OK.value(), "Lấy dữ liệu thành công", pageResponse);
        } catch (Exception ex) {
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Lấy dữ liệu không thành công", null);
        }
    }

    public Object detail(long groupId) {
        Optional<GroupEntity> pageEntity = groupRepository.findById(groupId);
        if (pageEntity.isPresent()) {
            return new BaseResponse<>(HttpStatus.OK.value(), "Lấy dữ liệu chi tiết thành công", groupRepository.getDetail(groupId));
        }
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Lấy dữ liệu chi tiết thất bại", null);
    }

    public Object insert(GroupAddRequest groupAddRequest) {
        try {
            GroupEntity groupEntity = new GroupEntity();
            groupEntity.setGroupName(groupAddRequest.getGroupName());
            groupEntity.setPhone(groupAddRequest.getPhone());
            groupEntity.setLink(groupAddRequest.getLink());
            groupEntity.setMemTotal(groupAddRequest.getMemberTotal());
            groupEntity.setExpense(groupAddRequest.getExpense());
            List<IndustryEntity> entities = industryRepository.findByIdIn(groupAddRequest.getIndustryId());
            if (!entities.isEmpty()) {
                StringJoiner joiner = new StringJoiner(", ");
                entities.forEach(map -> {
                    joiner.add(map.getIndustryName());
                });
                groupEntity.setIndustry(joiner.toString());
                groupEntity.setIndustryId(InfluencerServiceImpl.parseListIntegerToString(groupAddRequest.getIndustryId()));
            }
            groupEntity.setCreated(new Date());
            groupEntity = groupRepository.save(groupEntity);
            return new BaseResponse<>(HttpStatus.CREATED.value(), "Thêm dữ liệu thành công!", groupRepository.getDetail(groupEntity.getId()));
        } catch (Exception e) {
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Thêm dữ liệu không thành công!", null);
        }
    }

    public Object edit(GroupEditRequest groupEditRequest) {
        try {
            Optional<GroupEntity> groupEntity = groupRepository.findById(groupEditRequest.getId());
            if (groupEntity.isPresent()) {
                groupEntity.get().setGroupName(groupEditRequest.getGroupName());
                groupEntity.get().setPhone(groupEditRequest.getPhone());
                groupEntity.get().setLink(groupEditRequest.getLink());
                groupEntity.get().setMemTotal(groupEditRequest.getMemberTotal());
                groupEntity.get().setExpense(groupEditRequest.getExpense());
                List<IndustryEntity> entities = industryRepository.findByIdIn(groupEditRequest.getIndustryId());
                if (!entities.isEmpty()) {
                    StringJoiner joiner = new StringJoiner(", ");
                    entities.forEach(map -> {
                        joiner.add(map.getIndustryName());
                    });
                    groupEntity.get().setIndustry(joiner.toString());
                    groupEntity.get().setIndustryId(InfluencerServiceImpl.parseListIntegerToString(groupEditRequest.getIndustryId()));
                }
                groupRepository.save(groupEntity.get());
                return new BaseResponse<>(HttpStatus.CREATED.value(), "Sửa dữ liệu thành công!", groupRepository.getDetail(groupEntity.get().getId()));
            }
            return new ErrorResponse(HttpStatus.FAILED_DEPENDENCY.value(), "Sửa dữ liệu không thành công!", null);
        } catch (Exception e) {
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Sửa dữ liệu không thành công!", null);
        }
    }

    public Object delete(long groupId) {
        try {
            Optional<GroupEntity> pageEntity = groupRepository.findById(groupId);
            if (pageEntity.isPresent()) {
                groupRepository.deleteById(groupId);
                return new BaseResponse<>(HttpStatus.OK.value(), "Xóa dữ liệu thành công", null);
            }
            return new ErrorResponse(HttpStatus.FAILED_DEPENDENCY.value(), "Xóa dữ liệu không thành công!", null);
        } catch (Exception e) {
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Xóa dữ liệu không thành công!", null);
        }
    }
}
