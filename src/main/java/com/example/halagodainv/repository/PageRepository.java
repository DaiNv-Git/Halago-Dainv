package com.example.halagodainv.repository;

import com.example.halagodainv.dto.news.NewDto;
import com.example.halagodainv.dto.page.PageDto;
import com.example.halagodainv.model.PageEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageRepository extends JpaRepository<PageEntity, Long> {

    @Query("select new com.example.halagodainv.dto.page.PageDto(p.id,p.namePage,p.phone,p.link,p.follower,p.expense,i.industryName,p.created) from PageEntity p left join IndustryEntity i on p.industryId = i.id where p.id =:id")
    PageDto getDetailPage(@Param("id") long pageId);

    @Query("select new com.example.halagodainv.dto.page.PageDto(p.id,p.namePage,p.phone,p.link,p.follower,p.expense,i.industryName,p.created) " +
            "from PageEntity p left join IndustryEntity i on p.industryId = i.id where " +
            "ifnull(i.industryName,'') like concat('%',:industryName,'%') AND " +
            "ifnull(p.expense,'') like concat('%',:expense,'%') AND " +
            "ifnull(p.follower ,'') like concat('%',:follower,'%') AND " +
            "ifnull(p.namePage ,'') like concat('%',:namePage,'%')")
    List<PageDto> getPages(@Param("industryName") String industryName, @Param("expense") String expense, @Param("follower") String follower, @Param("namePage") String namePage, Pageable pageable);

    @Query("select count (p)" +
            "from PageEntity p left join IndustryEntity i on p.industryId = i.id where " +
            "ifnull(i.industryName,'') like concat('%',:industryName,'%') AND " +
            "ifnull(p.expense,'') like concat('%',:expense,'%') AND " +
            "ifnull(p.follower ,'') like concat('%',:follower,'%') AND " +
            "ifnull(p.namePage ,'') like concat('%',:namePage,'%')")
    long countByAll(@Param("industryName") String industryName, @Param("expense") String expense, @Param("follower") String follower, @Param("namePage") String namePage);
}
