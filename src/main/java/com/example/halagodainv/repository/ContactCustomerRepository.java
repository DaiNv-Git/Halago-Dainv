package com.example.halagodainv.repository;


import com.example.halagodainv.model.ContactCustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactCustomerRepository extends JpaRepository<ContactCustomerEntity, Integer> {
    Optional<ContactCustomerEntity> findByPhoneOrEmail(String phone, String email);

}
