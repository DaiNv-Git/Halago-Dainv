package com.example.halagodainv.repository.viewdisplay;

import com.example.halagodainv.model.viewdisplayentity.ViewBrandEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewBrandRepository extends JpaRepository<ViewBrandEntity, Long> {
    List<ViewBrandEntity> findAllBy(Pageable pageable);
}
