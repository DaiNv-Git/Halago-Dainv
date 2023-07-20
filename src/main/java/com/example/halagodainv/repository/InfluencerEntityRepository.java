package com.example.halagodainv.repository;

import com.example.halagodainv.dto.influcer.InflucerDto;
import com.example.halagodainv.model.InfluencerEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfluencerEntityRepository extends JpaRepository<InfluencerEntity, Long> {

    @Query("SELECT new com.example.halagodainv.dto.influcer.InflucerDto(ie.id,ie.influcerName,ie.isFacebook,ie.isTiktok,ie.isInstagram,ie.isYoutube,ie.industry,ie.phone)  " +
            "FROM InfluencerEntity ie " +
            "WHERE ((:#{#isFacebook} is null and (ie.isFacebook = true or ie.isFacebook = false)) or ie.isFacebook =:#{#isFacebook}) and " +
            "((:#{#isYoutube} is null and (ie.isYoutube = true or ie.isYoutube = false)) or ie.isYoutube =:#{#isYoutube}) and" +
            "((:#{#isInstagram} is null and (ie.isInstagram = true or ie.isInstagram = false)) or ie.isInstagram =:#{#isInstagram}) and" +
            "((:#{#isTiktok} is null and (ie.isTiktok = true or ie.isTiktok = false)) or ie.isTiktok =:#{#isTiktok}) and " +
            "ie.industry like concat('%',:#{#industry},'%') and " +
            "(:#{#provinceId} = 0 or ie.provinceId =:#{#provinceId}) ")
    List<InflucerDto> getAll(@Param("isFacebook") Boolean isFacebook,
                             @Param("isYoutube") Boolean isYoutube,
                             @Param("isInstagram") Boolean isInstagram,
                             @Param("isTiktok") Boolean isTiktok,
                             @Param("industry") String industry,
                             @Param("provinceId") int provinceId, Pageable pageable);

    @Query("SELECT count(ie) FROM InfluencerEntity ie " +
            "WHERE ((:#{#isFacebook} is null and (ie.isFacebook = true or ie.isFacebook = false)) or ie.isFacebook =:#{#isFacebook}) and " +
            "((:#{#isYoutube} is null and (ie.isYoutube = true or ie.isYoutube = false)) or ie.isYoutube =:#{#isYoutube}) and" +
            "((:#{#isInstagram} is null and (ie.isInstagram = true or ie.isInstagram = false)) or ie.isInstagram =:#{#isInstagram}) and" +
            "((:#{#isTiktok} is null and (ie.isTiktok = true or ie.isTiktok = false)) or ie.isTiktok =:#{#isTiktok}) and " +
            "ie.industry like concat('%',:#{#industry},'%') and " +
            "(:#{#provinceId} = 0 or ie.provinceId =:#{#provinceId}) ")
    int totalCount(@Param("isFacebook") Boolean isFacebook,
                   @Param("isYoutube") Boolean isYoutube,
                   @Param("isInstagram") Boolean isInstagram,
                   @Param("isTiktok") Boolean isTiktok,
                   @Param("industry") String industry,
                   @Param("provinceId") int provinceId);

}
