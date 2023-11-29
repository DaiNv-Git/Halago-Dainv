package com.example.halagodainv.repository;

import com.example.halagodainv.model.ImageProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ImageProductRepository extends JpaRepository<ImageProductEntity, Long> {
    Optional<ImageProductEntity> findByIdAndCampaignEntity_Id(Integer integer, Integer campaignId);

    List<ImageProductEntity> findByCampaignEntity_Id(int campaignId);

    @Transactional
    @Modifying
    void deleteByCampaignEntity_Id(int campaignId);
}
