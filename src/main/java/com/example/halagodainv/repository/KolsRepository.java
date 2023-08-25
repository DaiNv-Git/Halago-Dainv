package com.example.halagodainv.repository;


import com.example.halagodainv.model.RepresentativeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KolsRepository extends JpaRepository<RepresentativeEntity,String> {


}
