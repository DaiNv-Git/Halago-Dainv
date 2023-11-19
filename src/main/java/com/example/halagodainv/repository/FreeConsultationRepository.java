package com.example.halagodainv.repository;

import com.example.halagodainv.model.viewdisplayentity.FreeConsultationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FreeConsultationRepository extends JpaRepository<FreeConsultationEntity, Long> {
    Optional<FreeConsultationEntity> findByPhoneOrEmail(String phone,String email);


}
