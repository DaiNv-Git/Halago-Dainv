package com.example.halagodainv.repository;


import com.example.halagodainv.model.FunctionApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FunctionApiRepository extends JpaRepository<FunctionApi,String> {
//    @Query("Select funApi from FunctionApi funApi where funApi.idFunction=:id")
//    FunctionApi findFunctionApi(@Param("id")int id);
}
