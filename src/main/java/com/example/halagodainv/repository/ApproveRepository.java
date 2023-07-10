package com.example.halagodainv.repository;


import com.example.halagodainv.model.Approve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApproveRepository extends JpaRepository<Approve, String> {

}
