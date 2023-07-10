package com.example.halagodainv.repository;


import com.example.halagodainv.model.Facebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface fbRepository extends JpaRepository<Facebook,Integer> {

}
