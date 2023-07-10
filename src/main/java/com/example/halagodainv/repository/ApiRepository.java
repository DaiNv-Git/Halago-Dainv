package com.example.halagodainv.repository;


import com.example.halagodainv.model.ApiEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiRepository extends JpaRepository<ApiEntity,String> {

}
