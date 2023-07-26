package com.example.halagodainv.repository;

import com.example.halagodainv.dto.brand.BrandLogoHomeDto;
import com.example.halagodainv.dto.campain.CampaignBranDto;
import com.example.halagodainv.model.BrandEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM brand br WHERE br.brand_name LIKE CONCAT('%',:brandName, '%') AND " +
            "(br.created >=  STR_TO_DATE(:startDate, '%Y-%m-%d %H:%i:%s') AND " +
            "br.created <=  STR_TO_DATE(:endDate, '%Y-%m-%d %H:%i:%s'))ORDER BY br.id DESC ")
    List<BrandEntity> findByBrandNameAndCreatedDate(@Param("brandName") String brandName, @Param("startDate") String startDate, @Param("endDate") String endDate, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT count(*) FROM brand br WHERE br.brand_name LIKE CONCAT('%',:brandName, '%') AND " +
            "(br.created >=  STR_TO_DATE(:startDate, '%Y-%m-%d %H:%i:%s') AND " +
            "br.created <=  STR_TO_DATE(:endDate, '%Y-%m-%d %H:%i:%s'))ORDER BY br.id DESC ")
    int countAllBy(@Param("brandName") String brandName, @Param("startDate") String startDate, @Param("endDate") String endDate);

    @Query("SELECT new com.example.halagodainv.dto.campain.CampaignBranDto(br.id, br.brandName) FROM BrandEntity br ")
    List<CampaignBranDto> findByBrandNameAndId();

    Optional<BrandEntity> findByBrandEmail(String email);

    Optional<BrandEntity> findByBrandName(String name);

    @Query("SELECT new com.example.halagodainv.dto.brand.BrandLogoHomeDto(br.logo) FROM BrandEntity br " +
            "where br.partnerId =:partnerId order by br.created desc ")
    List<BrandLogoHomeDto> getByLogo(@Param("partnerId") int partnerId);
}
