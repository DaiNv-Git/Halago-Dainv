package com.example.halagodainv.repository.viewdisplay;

import com.example.halagodainv.model.viewdisplayentity.BrandGiveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandGiveRepository extends JpaRepository<BrandGiveEntity,Long> {
}
