package com.example.halagodainv.repository.viewdisplay;

import com.example.halagodainv.model.viewdisplayentity.PartnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PartnerRepository extends JpaRepository<PartnerEntity, Long> {

    List<PartnerEntity> findByPartnerIdOrderByIndexLogoAsc(Integer partnerId);

    @Transactional
    @Modifying
    void deleteByPartnerId(Integer partnerId);
}
