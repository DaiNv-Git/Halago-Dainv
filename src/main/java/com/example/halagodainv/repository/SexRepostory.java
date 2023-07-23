package com.example.halagodainv.repository;

import com.example.halagodainv.model.SexEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SexRepostory extends JpaRepository<SexEntity, Integer> {

    SexEntity findByName(String name);
}
