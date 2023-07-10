package com.example.halagodainv.repository;

import com.example.halagodainv.model.AboutUs;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutUsRepository extends CrudRepository<AboutUs,String> {

}
