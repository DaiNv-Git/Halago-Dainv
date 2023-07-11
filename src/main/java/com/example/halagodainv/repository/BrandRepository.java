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

    @Query(nativeQuery = true, value = "SELECT * FROM brand br WHERE br.brand_name LIKE CONCAT('%',:brandName, '%') "+
            "ORDER BY br.id DESC")
    List<BrandEntity> findByBrandNameAndStatus(@Param("brandName") String brandName, Pageable pageable);

    int countAllBy();
}
