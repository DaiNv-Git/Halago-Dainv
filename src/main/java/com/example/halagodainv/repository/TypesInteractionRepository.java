package com.example.halagodainv.repository;


import com.example.halagodainv.model.TypesInteraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypesInteractionRepository extends JpaRepository<TypesInteraction,String> {
//    @Query("Select type from TypesInteraction type")
//    List<TypesInteraction> listType();

}
