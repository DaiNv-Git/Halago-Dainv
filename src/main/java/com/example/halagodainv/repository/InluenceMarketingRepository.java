package com.example.halagodainv.repository;

import com.example.halagodainv.model.InfluenceMarketing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InluenceMarketingRepository extends JpaRepository<InfluenceMarketing, Integer> {
}
