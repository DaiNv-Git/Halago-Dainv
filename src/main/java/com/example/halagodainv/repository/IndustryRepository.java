package com.example.halagodainv.repository;


import com.example.halagodainv.model.Industry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndustryRepository extends JpaRepository<Industry,String> {
//    @Query("Select indus from Industry indus")
//    List<Industry> listIndustry ();
}
