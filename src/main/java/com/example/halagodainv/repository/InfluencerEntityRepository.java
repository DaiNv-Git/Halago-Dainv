package com.example.halagodainv.repository;

import com.example.halagodainv.dto.influcer.*;
import com.example.halagodainv.model.InfluencerEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfluencerEntityRepository extends JpaRepository<InfluencerEntity, Long> {

    @Query("SELECT new com.example.halagodainv.dto.influcer.InflucerMenuDto(ie.id,ie.influcerName,ie.isFacebook,ie.isTiktok,ie.isInstagram,ie.isYoutube,ie.industry,ie.phone)  " +
            "FROM InfluencerEntity ie " +
            "WHERE ((:#{#isFacebook} is null and (ie.isFacebook = true or ie.isFacebook = false)) or ie.isFacebook =:#{#isFacebook}) and " +
            "((:#{#isYoutube} is null and (ie.isYoutube = true or ie.isYoutube = false)) or ie.isYoutube =:#{#isYoutube}) and" +
            "((:#{#isInstagram} is null and (ie.isInstagram = true or ie.isInstagram = false)) or ie.isInstagram =:#{#isInstagram}) and" +
            "((:#{#isTiktok} is null and (ie.isTiktok = true or ie.isTiktok = false)) or ie.isTiktok =:#{#isTiktok}) and " +
            "ie.industry like concat('%',:#{#industry},'%') and " +
            "(:#{#provinceId} = 0 or ie.provinceId =:#{#provinceId}) and " +
            "(:#{#sex} = 0 or ie.sex =:#{#sex}) and " +
            "ie.yearOld like concat('%',:#{#birtYear},'%') ")
    List<InflucerMenuDto> getAll(@Param("isFacebook") Boolean isFacebook,
                                 @Param("isYoutube") Boolean isYoutube,
                                 @Param("isInstagram") Boolean isInstagram,
                                 @Param("isTiktok") Boolean isTiktok,
                                 @Param("industry") String industry,
                                 @Param("provinceId") int provinceId,
                                 @Param("sex") int sex,
                                 @Param("birtYear") String birtYear
            , Pageable pageable);

    @Query("SELECT count(ie) FROM InfluencerEntity ie " +
            "WHERE ((:#{#isFacebook} is null and (ie.isFacebook = true or ie.isFacebook = false)) or ie.isFacebook =:#{#isFacebook}) and " +
            "((:#{#isYoutube} is null and (ie.isYoutube = true or ie.isYoutube = false)) or ie.isYoutube =:#{#isYoutube}) and" +
            "((:#{#isInstagram} is null and (ie.isInstagram = true or ie.isInstagram = false)) or ie.isInstagram =:#{#isInstagram}) and" +
            "((:#{#isTiktok} is null and (ie.isTiktok = true or ie.isTiktok = false)) or ie.isTiktok =:#{#isTiktok}) and " +
            "ie.industry like concat('%',:#{#industry},'%') and " +
            "(:#{#provinceId} = 0 or ie.provinceId =:#{#provinceId})and " +
            "(:#{#sex} = 0 or ie.sex =:#{#sex}) and " +
            "ie.yearOld like concat('%',:#{#birtYear},'%') ")
    int totalCount(@Param("isFacebook") Boolean isFacebook,
                   @Param("isYoutube") Boolean isYoutube,
                   @Param("isInstagram") Boolean isInstagram,
                   @Param("isTiktok") Boolean isTiktok,
                   @Param("industry") String industry,
                   @Param("provinceId") int provinceId,
                   @Param("sex") int sex,
                   @Param("birtYear") String birtYear);

    @Query("SELECT new com.example.halagodainv.dto.influcer.InflucerMenuDto(ie.id,ie.influcerName,ie.isFacebook,ie.isTiktok,ie.isInstagram,ie.isYoutube,ie.industry,ie.phone) " +
            "FROM InfluencerEntity ie left join InfluencerDetailEntity id on ie.id= id.influId WHERE " +
            "((:#{#isFacebook} is null and (ie.isFacebook = true or ie.isFacebook = false)) or (ie.isFacebook =:#{#isFacebook} and id.channel ='FACEBOOK')) and " +
            "((:#{#isYoutube} is null and (ie.isYoutube = true or ie.isYoutube = false)) or (ie.isYoutube = :#{#isYoutube} and id.channel ='YOUTUBE')) and" +
            "((:#{#isInstagram} is null and (ie.isInstagram = true or ie.isInstagram = false)) or (ie.isInstagram = :#{#isInstagram} and id.channel ='INSTAGRAM')) and" +
            "((:#{#isTiktok} is null and (ie.isTiktok = true or ie.isTiktok = false)) or (ie.isTiktok = :#{#isTiktok} and id.channel ='TIKTOK')) and " +
            "ie.industry like concat('%',:#{#industry},'%') and " +
            "id.expense like concat('%',:#{#expense},'%') and " +
            "id.follower like concat('%',:#{#follower},'%') and " +
            "(:#{#provinceId} = 0 or ie.provinceId =:#{#provinceId})  and " +
            "(:#{#sex} = 0 or ie.sex =:#{#sex}) and " +
            "ie.yearOld like concat('%',:#{#birtYear},'%') ")
    List<InflucerMenuDto> getFilterMenu(@Param("isFacebook") Boolean isFacebook,
                                        @Param("isYoutube") Boolean isYoutube,
                                        @Param("isInstagram") Boolean isInstagram,
                                        @Param("isTiktok") Boolean isTiktok,
                                        @Param("industry") String industry,
                                        @Param("provinceId") int provinceId,
                                        @Param("expense") String expense,
                                        @Param("follower") String follower,
                                        @Param("sex") int sex,
                                        @Param("birtYear") String birtYear,
                                        Pageable pageable);

    @Query("SELECT count(ie) " +
            "from InfluencerEntity ie left join InfluencerDetailEntity id on ie.id= id.influId where " +
            "((:#{#isFacebook} is null and (ie.isFacebook = true or ie.isFacebook = false)) or (ie.isFacebook =:#{#isFacebook} and id.channel ='FACEBOOK')) and " +
            "((:#{#isYoutube} is null and (ie.isYoutube = true or ie.isYoutube = false)) or (ie.isYoutube = :#{#isYoutube} and id.channel ='YOUTUBE')) and" +
            "((:#{#isInstagram} is null and (ie.isInstagram = true or ie.isInstagram = false)) or (ie.isInstagram = :#{#isInstagram} and id.channel ='INSTAGRAM')) and" +
            "((:#{#isTiktok} is null and (ie.isTiktok = true or ie.isTiktok = false)) or (ie.isTiktok = :#{#isTiktok} and id.channel ='TIKTOK')) and " +
            "ie.industry like concat('%',:#{#industry},'%') and " +
            "id.expense like concat('%',:#{#expense},'%') and " +
            "id.follower like concat('%',:#{#follower},'%') and " +
            "(:#{#provinceId} = 0 or ie.provinceId =:#{#provinceId})  and " +
            "(:#{#sex} = 0 or ie.sex =:#{#sex}) and " +
            "ie.yearOld like concat('%',:#{#birtYear},'%') ")
    long countFilterMenu(@Param("isFacebook") Boolean isFacebook,
                         @Param("isYoutube") Boolean isYoutube,
                         @Param("isInstagram") Boolean isInstagram,
                         @Param("isTiktok") Boolean isTiktok,
                         @Param("industry") String industry,
                         @Param("provinceId") int provinceId,
                         @Param("expense") String expense,
                         @Param("follower") String follower,
                         @Param("sex") int sex,
                         @Param("birtYear") String birtYear);


    @Query("select new com.example.halagodainv.dto.influcer.InflucerDtoSubMenu(ie.id,ie.influcerName,ie.phone,id.url,id.follower,id.expense,ie.industry) from InfluencerEntity ie " +
            "left join InfluencerDetailEntity id on ie.id= id.influId  " +
            "WHERE (:#{#isFacebook} is null or (ie.isFacebook =:#{#isFacebook} and id.channel ='FACEBOOK')) and " +
            "(:#{#isYoutube} is null or (ie.isYoutube = :#{#isYoutube} and id.channel ='YOUTUBE')) and " +
            "(:#{#isInstagram} is null or (ie.isInstagram = :#{#isInstagram} and id.channel ='INSTAGRAM')) and " +
            "(:#{#isTiktok} is null or (ie.isTiktok = :#{#isTiktok} and id.channel ='TIKTOK')) and " +
            "ie.industry like concat('%',:#{#industry},'%') and " +
            "id.expense like concat('%',:#{#expense},'%') and " +
            "id.follower like concat('%',:#{#follower},'%') and " +
            "(:#{#provinceId} = 0 or ie.provinceId =:#{#provinceId}) and " +
            "(:#{#sex} = 0 or ie.sex =:#{#sex}) and " +
            "ie.yearOld like concat('%',:#{#birtYear},'%') ")
    List<InflucerDtoSubMenu> getSubMenu(@Param("isFacebook") Boolean isFacebook,
                                        @Param("isYoutube") Boolean isYoutube,
                                        @Param("isInstagram") Boolean isInstagram,
                                        @Param("isTiktok") Boolean isTiktok,
                                        @Param("industry") String industry,
                                        @Param("expense") String expense,
                                        @Param("follower") String follower,
                                        @Param("provinceId") int provinceId,
                                        @Param("sex") int sex,
                                        @Param("birtYear") String birtYear,
                                        Pageable pageable);

    @Query("select count(ie)from InfluencerEntity ie " +
            "left join InfluencerDetailEntity id on ie.id= id.influId  " +
            "WHERE (:#{#isFacebook} is null or (ie.isFacebook =:#{#isFacebook} and id.channel ='FACEBOOK')) and " +
            "(:#{#isYoutube} is null or (ie.isYoutube = :#{#isYoutube} and id.channel ='YOUTUBE')) and " +
            "(:#{#isInstagram} is null or (ie.isInstagram = :#{#isInstagram} and id.channel ='INSTAGRAM')) and " +
            "(:#{#isTiktok} is null or (ie.isTiktok = :#{#isTiktok} and id.channel ='TIKTOK')) and " +
            "ie.industry like concat('%',:#{#industry},'%') and " +
            "id.expense like concat('%',:#{#expense},'%') and " +
            "id.follower like concat('%',:#{#follower},'%') and " +
            "(:#{#provinceId} = 0 or ie.provinceId =:#{#provinceId}) and " +
            "(:#{#sex} = 0 or ie.sex =:#{#sex}) and " +
            "ie.yearOld like concat('%',:#{#birtYear},'%') ")
    long countSubMenu(@Param("isFacebook") Boolean isFacebook,
                      @Param("isYoutube") Boolean isYoutube,
                      @Param("isInstagram") Boolean isInstagram,
                      @Param("isTiktok") Boolean isTiktok,
                      @Param("industry") String industry,
                      @Param("expense") String expense,
                      @Param("follower") String follower,
                      @Param("provinceId") int provinceId,
                      @Param("sex") int sex,
                      @Param("birtYear") String birtYear);


    @Query("select new com.example.halagodainv.dto.influcer.InfluencerExportExcelDto(ie.id,ie.influcerName,e.name,id.url,id.follower,id.expense,ie.address,ie.industryName,ie.classifyName) from InfluencerEntity ie " +
            "left join InfluencerDetailEntity id on ie.id= id.influId left join SexEntity e on e.id = ie.sex " +
            "WHERE (:#{#isFacebook} is null or (ie.isFacebook =:#{#isFacebook} and id.channel ='FACEBOOK')) and " +
            "(:#{#isYoutube} is null or (ie.isYoutube = :#{#isYoutube} and id.channel ='YOUTUBE')) and " +
            "(:#{#isInstagram} is null or (ie.isInstagram = :#{#isInstagram} and id.channel ='INSTAGRAM')) and " +
            "(:#{#isTiktok} is null or (ie.isTiktok = :#{#isTiktok} and id.channel ='TIKTOK')) and " +
            "ie.industry like concat('%',:#{#industry},'%') and " +
            "id.expense like concat('%',:#{#expense},'%') and " +
            "id.follower like concat('%',:#{#follower},'%') and " +
            "(:#{#provinceId} = 0 or ie.provinceId =:#{#provinceId}) and " +
            "(:#{#sex} = 0 or ie.sex =:#{#sex}) and " +
            "ie.yearOld like concat('%',:#{#birtYear},'%') order by ie.id desc ")
    List<InfluencerExportExcelDto> getExportExcel(@Param("isFacebook") Boolean isFacebook,
                                                  @Param("isYoutube") Boolean isYoutube,
                                                  @Param("isInstagram") Boolean isInstagram,
                                                  @Param("isTiktok") Boolean isTiktok,
                                                  @Param("industry") String industry,
                                                  @Param("expense") String expense,
                                                  @Param("follower") String follower,
                                                  @Param("provinceId") int provinceId,
                                                  @Param("sex") int sex,
                                                  @Param("birtYear") String birtYear);

    @Query("select new com.example.halagodainv.dto.influcer.InflucerDtoListDetail(ie.id,ie.influcerName,ie.isFacebook, " +
            "ie.isTiktok,ie.isInstagram,ie.isYoutube,ie.industry,ie.phone,ie.sex,ie.yearOld,ie.classifyId,ie.provinceId,ie.address,ie.bankId,ie.accountNumber," +
            "id.follower,id.expense,id.url,id.channel,ie.historyCreated,ie.email) from InfluencerEntity ie " +
            "left join InfluencerDetailEntity id on ie.id= id.influId  " +
            "WHERE ie.id = :#{#id}")
    List<InflucerDtoListDetail> getDetails(@Param("id") long id);

}
