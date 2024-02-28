package com.example.halagodainv.repository;

import com.example.halagodainv.dto.influcer.*;
import com.example.halagodainv.model.InfluencerEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface InfluencerEntityRepository extends JpaRepository<InfluencerEntity, Long> {
    Optional<InfluencerEntity> findByEmail(String email);
    Optional<InfluencerEntity> findByUserId(Integer userId);

    Optional<InfluencerEntity> findByPhone(String phone);

    Optional<InfluencerEntity> findByInflucerName(String name);
    @Transactional
    @Modifying
    void deleteByUserId(int userId);


    @Query("select new com.example.halagodainv.dto.influcer.InflucerDtoListDetail(ie.id,ie.influcerName,ie.isFacebook, " +
            "ie.isTiktok,ie.isInstagram,ie.isYoutube,ie.industry,ie.phone,ie.sex,ie.yearOld,ie.classifyId,ie.provinceId,ie.address,ie.bankId,ie.accountNumber, " +
            "id.follower,id.expense,id.url,id.channel,ie.historyCreated,ie.email) from InfluencerEntity ie " +
            "left join InfluencerDetailEntity id on ie.id= id.influId  " +
            "WHERE ie.id = :#{#id}")
    List<InflucerDtoListDetail> getDetails(@Param("id") long id);

}
