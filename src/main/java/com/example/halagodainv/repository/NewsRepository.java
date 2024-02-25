package com.example.halagodainv.repository;


import com.example.halagodainv.dto.hompage.NewsTenDto;
import com.example.halagodainv.dto.news.NewDetails;
import com.example.halagodainv.dto.news.NewDto;
import com.example.halagodainv.dto.viewnews.ViewNewAndHot;
import com.example.halagodainv.dto.viewnews.ViewNewsMap;
import com.example.halagodainv.model.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Integer> {
    @Query("select new com.example.halagodainv.dto.news.NewDto(n.idNews,nl.title,n.thumbnail,c.categoryName,n.created,n.topicId,n.tagId,n.tagName) " +
            "from NewsEntity n left join NewsLanguageEntity nl " +
            "on n.idNews = nl.newsEntity.idNews " +
            "left join CategoryEntity c on c.id = n.type " +
            "where upper(nl.language) ='VN' " +
            "and IFNULL(nl.title,'') like concat('%',:title,'%') " +
            "and IFNULL(n.tagId,'') like concat('%',:tagId,'%') " +
            "and (:topicId is null or :topicId = 0l or n.topicId =:topicId ) and (:isHot is null or n.isHot =:isHot) " +
            "ORDER BY n.idNews DESC  ")
    List<NewDto> getNewList(@Param("title") String title, @Param("topicId") Long topicId, @Param("tagId") String tagId,@Param("isHot") Boolean isHot, Pageable pageable);

    @Query(value = "select count(n) from NewsEntity n left join NewsLanguageEntity nl " +
            "on n.idNews = nl.newsEntity.idNews " +
            "left join CategoryEntity c on c.id = n.type where nl.language ='VN' " +
            "and IFNULL(nl.title,'') like concat('%',:title,'%') " +
            "and IFNULL(n.tagId,'') like concat('%',:tagId,'%') " +
            "and (:topicId is null or :topicId = 0l or n.topicId =:topicId ) and (:isHot is null or n.isHot =:isHot) ")
    int countByAll(@Param("title") String title, @Param("topicId") Long topicId, @Param("tagId") String tagId,@Param("isHot") Boolean isHot);


    @Query("select new com.example.halagodainv.dto.news.NewDetails(n.idNews,nl.title,n.thumbnail, " +
            "nl.description,nl.content,c.id,n.titleSeo,n.linkPapers,nl.language,n.topicId,n.tagId,n.isHot,n.authorName,n.authorAvatar,n.tagName) " +
            "from NewsEntity n left join NewsLanguageEntity nl " +
            "on n.idNews = nl.newsEntity.idNews " +
            "left join CategoryEntity c on c.id = n.type where n.idNews =:idNews ")
    List<NewDetails> getHomeLanguage(@Param("idNews") int idNews);

    @Query(value = "select new com.example.halagodainv.dto.hompage.NewsTenDto(n.linkPapers,n.titleSeo,pl.title, n.thumbnail,pl.description,n.created,n.authorAvatar,n.authorName) " +
            "from NewsEntity n left join NewsLanguageEntity pl on pl.newsEntity.idNews=n.idNews where n.topicId=1l " +
            "and pl.language =:language order by n.created desc ")
    List<NewsTenDto> getHomeLanguage(@Param("language") String language, Pageable pageable);


    @Query("select new com.example.halagodainv.dto.viewnews.ViewNewsMap(n.idNews,nl.title,nl.content,n.created,n.topicId,n.tagId,n.thumbnail) from  NewsEntity n " +
            "left join NewsLanguageEntity nl on n.idNews = nl.newsEntity.idNews " +
            "where (:#{#topicId} = 0l or n.topicId=:#{#topicId}) and IFNULL(n.tagId,'') like concat('%',:#{#tagId},'%') and nl.language =:#{#language} ")
    List<ViewNewsMap> getViewNews(@Param("topicId") Long topicId, @Param("tagId") String tagId, @Param("language") String language);

    @Query("select new com.example.halagodainv.dto.viewnews.ViewNewsMap(n.idNews,nl.title,nl.content,n.created,n.topicId,n.tagId,n.thumbnail) from  NewsEntity n " +
            "left join NewsLanguageEntity nl on n.idNews = nl.newsEntity.idNews " +
            "where (:#{#topicId} = 0l or n.topicId=:#{#topicId}) and IFNULL(n.tagId,'') like concat('%',:#{#tagId},'%') and nl.language = :#{#language} ")
    List<ViewNewsMap> getPageViewNews(@Param("topicId") Long topicId, @Param("tagId") String tagId, @Param("language") String language, Pageable pageable);

    @Query("select count (n) from  NewsEntity n " +
            "left join NewsLanguageEntity nl on n.idNews = nl.newsEntity.idNews " +
            "where (:#{#topicId} = 0l or n.topicId=:#{#topicId}) and IFNULL(n.tagId,'') like concat('%',:#{#tagId},'%')  and nl.language =:#{#language}")
    int getCountPageViewNews(@Param("topicId") Long topicId, @Param("tagId") String tagId, @Param("language") String language);

    @Query("select new com.example.halagodainv.dto.viewnews.ViewNewsMap(n.idNews,nl.title,nl.content,n.created,n.topicId,n.tagId,n.thumbnail) from  NewsEntity n " +
            "left join NewsLanguageEntity nl on n.idNews = nl.newsEntity.idNews " +
            "where nl.language =:#{#language} and n.idNews =:#{#id}")
    ViewNewsMap getDetailView(@Param("language") String language, @Param("id") Integer id);

    @Query("select new com.example.halagodainv.dto.viewnews.ViewNewAndHot(n.idNews,nl.title,n.thumbnail) from  NewsEntity n " +
            "left join NewsLanguageEntity nl on n.idNews = nl.newsEntity.idNews " +
            "where (:#{#topicId} = 0l or n.topicId=:#{#topicId}) and IFNULL(n.tagId,'') like concat('%',:#{#tagId},'%') and nl.language =:#{#language} and n.topicId <> 1 and n.topicId <> 6  ")
    List<ViewNewAndHot> getViewNew(@Param("topicId") Long topicId, @Param("tagId") String tagId, @Param("language") String language, Pageable pageable);

    @Query("select new com.example.halagodainv.dto.viewnews.ViewNewAndHot(n.idNews,nl.title,n.thumbnail) from  NewsEntity n " +
            "left join NewsLanguageEntity nl on n.idNews = nl.newsEntity.idNews " +
            "where (:#{#topicId} = 0l or n.topicId=:#{#topicId}) and IFNULL(n.tagId,'') like concat('%',:#{#tagId},'%') and nl.language =:#{#language} and n.isHot =true ")
    List<ViewNewAndHot> getViewhots(@Param("topicId") Long topicId, @Param("tagId") String tagId, @Param("language") String language, Pageable pageable);

    List<NewsEntity> findByIsHot(boolean isHot);

    void deleteAllByIsProduct(int isProduct);

    List<NewsEntity> findAllByIsProduct(int isProduct);
}
