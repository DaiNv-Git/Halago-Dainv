package com.example.halagodainv.repository;


import com.example.halagodainv.model.Function;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FunctionRepository extends JpaRepository<Function, String> {
//    @Query("Select fun from Function fun where fun.functionName=:name")
//    Function checkFunctionName(@Param("name") String name);
//
//    @Query("Select fun from Function fun where fun.id=:id")
//    Function findFunctionById(@Param("id") int id);
}
