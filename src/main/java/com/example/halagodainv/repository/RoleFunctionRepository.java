package com.example.halagodainv.repository;


import com.example.halagodainv.model.RoleFunction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleFunctionRepository extends JpaRepository<RoleFunction,String> {
//    @Query("Select role from RoleFunction role where role.idRole=:id")
//    RoleFunction findRoleById(@Param("id") int id);
}
