package com.example.halagodainv.repository;

import com.example.halagodainv.model.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Integer> {


    Optional<CityEntity> findByCityName(String name);
}
