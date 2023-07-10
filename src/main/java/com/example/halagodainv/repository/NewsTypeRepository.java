package com.example.halagodainv.repository;


import com.example.halagodainv.model.NewsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsTypeRepository extends JpaRepository<NewsType, String> {

}
