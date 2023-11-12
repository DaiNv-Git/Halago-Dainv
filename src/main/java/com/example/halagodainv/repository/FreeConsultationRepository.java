package com.example.halagodainv.repository;

import com.example.halagodainv.model.viewdisplayentity.FreeConsultationEntity;
import com.example.halagodainv.request.concatcustomer.FreeConsultationRequest;
import com.example.halagodainv.response.BaseResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FreeConsultationRepository extends JpaRepository<FreeConsultationEntity, Long> {
    Optional<FreeConsultationEntity> findByPhoneOrEmail(String phone,String email);


}
