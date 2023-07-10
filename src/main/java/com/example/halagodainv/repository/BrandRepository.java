package com.example.halagodainv.repository;

import com.example.halagodainv.model.BrandEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM brand br WHERE br.brand_name LIKE '%'|| :brandName ||'%' " +
            "AND br.status LIKE '%'|| :status ||'%' ORDER BY br.id DESC")
    List<BrandEntity> findByBrandNameAndStatus(@Param("brandName") String brandName, @Param("status") Integer status, Pageable pageable);

    int countAllBy();
}
