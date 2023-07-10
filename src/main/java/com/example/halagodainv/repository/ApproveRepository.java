package com.example.halagodainv.repository;


import com.example.halagodainv.model.ApproveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApproveRepository extends JpaRepository<ApproveEntity, String> {

}
