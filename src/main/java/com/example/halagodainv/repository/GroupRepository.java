package com.example.halagodainv.repository;

import com.example.halagodainv.dto.group.GroupDto;
import com.example.halagodainv.model.GroupEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
    @Query("select new com.example.halagodainv.dto.group.GroupDto(p.id,p.groupName,p.phone,p.link,p.memTotal,p.expense,p.created,i.industryName,p.industryId) from GroupEntity p left join IndustryEntity i on p.industryId = i.id where p.id =:id")
    GroupDto getDetail(@Param("id") long pageId);

    @Query("select new com.example.halagodainv.dto.group.GroupDto(p.id,p.groupName,p.phone,p.link,p.memTotal,p.expense,p.created,i.industryName,p.industryId) " +
            "from GroupEntity p left join IndustryEntity i on p.industryId = i.id where " +
            "ifnull(i.industryName,'') like concat('%',:industryName,'%') AND " +
            "ifnull(p.expense,'') like concat('%',:expense,'%') AND " +
            "ifnull(p.memTotal ,'') like concat('%',:memTotal,'%') AND " +
            "ifnull(p.groupName ,'') like concat('%',:groupName,'%')")
    List<GroupDto> getGroups(@Param("industryName") String industryName, @Param("expense") String expense, @Param("memTotal") String memTotal, @Param("groupName") String groupName, Pageable pageable);

    @Query("select count (p)" + "from GroupEntity p left join IndustryEntity i on p.industryId = i.id where " +
            "ifnull(i.industryName,'') like concat('%',:industryName,'%') AND " +
            "ifnull(p.expense,'') like concat('%',:expense,'%') AND " +
            "ifnull(p.memTotal ,'') like concat('%',:memTotal,'%') AND " +
            "ifnull(p.groupName ,'') like concat('%',:groupName,'%')")
    long countByAll(@Param("industryName") String industryName, @Param("expense") String expense, @Param("memTotal") String memTotal, @Param("groupName") String groupName);
}
