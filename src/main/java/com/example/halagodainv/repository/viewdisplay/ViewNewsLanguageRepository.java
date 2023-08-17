package com.example.halagodainv.repository.viewdisplay;

import com.example.halagodainv.model.viewdisplayentity.ViewNewsLanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewNewsLanguageRepository extends JpaRepository<ViewNewsLanguageEntity, Integer> {
}
