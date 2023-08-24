package com.example.halagodainv.repository.viewdisplay;

import com.example.halagodainv.model.viewdisplayentity.PartnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartnerRepository extends JpaRepository<PartnerEntity, Long> {

    List<PartnerEntity> findByPartnerId(Integer partnerId);
}
