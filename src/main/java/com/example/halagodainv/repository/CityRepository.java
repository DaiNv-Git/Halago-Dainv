package com.example.halagodainv.repository;
import com.example.halagodainv.model.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, String> {
    @Query(value = "SELECT * FROM city c WHERE (:name IS NULL  OR c.city_name LIKE %:name%)",nativeQuery = true)
    List<CityEntity> findByNameContainingIgnoreCase(String name);

}
