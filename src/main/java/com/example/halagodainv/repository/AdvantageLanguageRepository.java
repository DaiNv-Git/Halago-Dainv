package com.example.halagodainv.repository;

import com.example.halagodainv.model.AdvantageEntityLanguage;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvantageLanguageRepository extends JpaRepository<AdvantageEntityLanguage, Long> {
}