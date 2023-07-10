package com.example.halagodainv.repository;


import com.example.halagodainv.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
//    @Query("select ro from Role ro where (:name IS NULL OR lower(ro.name) LIKE lower(concat('%', concat(:name, '%'))))")
//    List<Role> findAll(@Param("name")String name);
//
//    @Query("Select fun from Role fun where fun.name=:name")
//    Role checkRoleName(@Param("name") String name);
//
//    @Query("Select role from Role role where role.idRole=:id")
//    Role findRoleById(@Param("id") int id);
}
