package com.example.halagodainv.repository;


import com.example.halagodainv.model.ContactCustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactCustomerRepository extends JpaRepository<ContactCustomerEntity,String> {

}
