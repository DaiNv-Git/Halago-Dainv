package com.example.halagodainv.repository;


import com.example.halagodainv.dto.hompage.NewsTenDto;
import com.example.halagodainv.dto.news.NewDetails;
import com.example.halagodainv.dto.news.NewDto;
import com.example.halagodainv.dto.viewnews.ViewNewsMap;
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
            "ORDER BY n.idNews DESC  ")
    List<NewDto> getNewList(@Param("title") String title, Pageable pageable);

    @Query(nativeQuery = true, value = "select count(*) from news n left join news_language nl " +
            "on n.id_news = nl.new_id " +
            "left join category c on c.id = n.type where nl.language ='VN' and nl.title like concat('%',:title,'%') " +
            "ORDER BY n.id_news DESC  ")
    int countByAll(@Param("title") String title);


    @Query("select new com.example.halagodainv.dto.news.NewDetails(n.idNews,nl.title,n.thumbnail," +
            "nl.description,nl.content,c.id,n.status,n.titleSeo,n.linkPapers,nl.language,nl.herder,nl.body,nl.footer,n.image1,n.image2,n.topicId,n.tagId) " +
            "from NewsEntity n left join NewsLanguageEntity nl " +
            "on n.idNews = nl.newsEntity.idNews " +
            "left join CategoryEntity c on c.id = n.type where n.idNews =:idNews ")
    List<NewDetails> getHomeLanguage(@Param("idNews") int idNews);

    @Query(value = "select new com.example.halagodainv.dto.hompage.NewsTenDto(n.titleSeo, n.thumbnail,n.created) " +
            "from NewsEntity n " +
            "order by n.created desc ")
    List<NewsTenDto> getHomeLanguage(Pageable pageable);


    @Query("select new com.example.halagodainv.dto.viewnews.ViewNewsMap(n.idNews,nl.title,nl.herder,nl.body,nl.footer,n.image1,n.image2,n.created,n.topicId,n.tagId) from  NewsEntity n " +
            "left join NewsLanguageEntity nl on n.idNews = nl.newsEntity.idNews " +
            "where (:#{#topicId} = 0l or n.topicId=:#{#topicId}) and (:#{#tagId} = 0l or n.tagId=:#{#tagId}) and nl.language =:#{#language} ")
    List<ViewNewsMap> getViewNews(@Param("topicId") Long topicId, @Param("tagId") Long tagId, @Param("language") String language, Pageable pageable);

    @Query("select new com.example.halagodainv.dto.viewnews.ViewNewsMap(n.idNews,nl.title,nl.herder,nl.body,nl.footer,n.image1,n.image2,n.created,n.topicId,n.tagId) from  NewsEntity n " +
            "left join NewsLanguageEntity nl on n.idNews = nl.newsEntity.idNews " +
            "where (:#{#topicId} = 0l or n.topicId=:#{#topicId}) and (:#{#tagId} = 0l or n.tagId=:#{#tagId}) and nl.language =:#{#language} and n.idNews =:#{#id} ")
    ViewNewsMap getDetailView(@Param("topicId") Long topicId, @Param("tagId") Long tagId, @Param("language") String language, @Param("id") Integer id);

    @Query("select new com.example.halagodainv.dto.viewnews.ViewNewsMap(n.idNews,nl.title,nl.herder,nl.body,nl.footer,n.image1,n.image2,n.created,n.topicId,n.tagId) from  NewsEntity n " +
            "left join NewsLanguageEntity nl on n.idNews = nl.newsEntity.idNews " +
            "where (:#{#topicId} = 0l or n.topicId=:#{#topicId}) and (:#{#tagId} = 0l or n.tagId=:#{#tagId}) and nl.language =:#{#language} and n.isHot= :#{#isHot}")
    List<ViewNewsMap> getViewNewTotalTopic(@Param("topicId") Long topicId, @Param("tagId") Long tagId, @Param("language") String language, @Param("isHot") Boolean isHot);
}
