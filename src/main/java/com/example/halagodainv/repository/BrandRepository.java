package com.example.halagodainv.repository;

import com.example.halagodainv.model.BrandEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM brand br WHERE br.brand_name LIKE CONCAT('%', :brandName, '%') AND " +
            "DATE_FORMAT(br.created,'%d-%m-%Y')  >= STR_TO_DATE(:startDate, '%d-%m-%Y')" +
            "AND DATE_FORMAT(br.created,'%d-%m-%Y')  <= STR_TO_DATE(:endDate, '%d-%m-%Y') " +
            "ORDER BY br.id DESC")
    List<BrandEntity> findByBrandNameAndStatus(@Param("brandName") String brandName, @Param("startDate") String startDate, @Param("endDate") String endDate, Pageable pageable);

    int countAllBy();
}
