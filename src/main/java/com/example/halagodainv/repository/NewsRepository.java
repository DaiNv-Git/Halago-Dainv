package com.example.halagodainv.repository;


import com.example.halagodainv.dto.news.NewDetails;
import com.example.halagodainv.dto.news.NewDto;
import com.example.halagodainv.model.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Integer> {
    @Query("select new com.example.halagodainv.dto.news.NewDto(n.idNews,nl.title,n.thumbnail,c.categoryName,n.created) from NewsEntity n left join NewsLanguageEntity nl " +
            "on n.idNews = nl.newsEntity.idNews " +
            "left join CategoryEntity c on c.id = n.type where nl.language ='VN' and nl.title like concat('%',:title,'%') " +
            "and(n.created >=  STR_TO_DATE(:startDate, '%Y-%m-%d %H:%i:%s') AND " +
            "n.created <=  STR_TO_DATE(:endDate, '%Y-%m-%d %H:%i:%s')) ORDER BY n.idNews DESC  ")
    List<NewDto> getNewList(@Param("title") String title, @Param("startDate") String startDate,
                            @Param("endDate") String endDate, Pageable pageable);

    @Query(nativeQuery = true,value = "select count(*) from news n left join news_language nl " +
            "on n.id_news = nl.new_id " +
            "left join category c on c.id = n.type where nl.language ='VN' and nl.title like concat('%',:title,'%') " +
            "and(n.created >=  STR_TO_DATE(:startDate, '%Y-%m-%d %H:%i:%s') AND " +
            "n.created <=  STR_TO_DATE(:endDate, '%Y-%m-%d %H:%i:%s')) ORDER BY n.id_news DESC  ")
    int countByAll(@Param("title") String title, @Param("startDate") String startDate,
                   @Param("endDate") String endDate);


    @Query("select new com.example.halagodainv.dto.news.NewDetails(n.idNews,nl.title,n.thumbnail," +
            "nl.description,nl.content,c.id,n.status,n.titleSeo,n.linkPapers,nl.language) " +
            "from NewsEntity n left join NewsLanguageEntity nl " +
            "on n.idNews = nl.newsEntity.idNews " +
            "left join CategoryEntity c on c.id = n.type where n.idNews =:idNews ")
    List<NewDetails> getDetail(@Param("idNews") int idNews);

}
