package com.example.halagodainv.repository;


import com.example.halagodainv.model.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Integer> {
    @Query(nativeQuery = true, value = "select * from news n left join news_language nl\n" +
            "on  n.id_news = nl.new_id where nl.language =:language AND nl.title like concat('%',:title,'%') " +
            "and(n.created >=  STR_TO_DATE(:startDate, '%Y-%m-%d %H:%i:%s') AND " +
            "n.created <=  STR_TO_DATE(:endDate, '%Y-%m-%d %H:%i:%s'))ORDER BY n.id_news DESC  ")
    List<NewsEntity> getNewList(@Param("language") String language, @Param("title") String title, @Param("startDate") String startDate,
                                @Param("endDate") String endDate, Pageable pageable);


    @Query(nativeQuery = true, value = "select COUNT(*) from news n " +
            "left join news_language nl on n.id_news = nl.new_id where nl.language =:language ")
    int countAllByNews(@Param("language") String language);

}
