package com.example.halagodainv.repository;

import com.example.halagodainv.model.AboutUsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutUsRepository extends CrudRepository<AboutUsEntity,String> {

}
