package com.example.halagodainv.repository;


import com.example.halagodainv.model.RolePersonal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePersonalRepository extends JpaRepository<RolePersonal, String> {
//    @Query("select rop from RolePersonal rop where rop.status = 0 or rop.status =1")
//    List<RolePersonal> findAll();
//
//    @Query("select rop from RolePersonal rop where  rop.idRolePersonal=:id and rop.status = 0 or rop.status =1")
//    RolePersonal findRolePersonal(@Param("id") int id);
//
//    @Query("select rop from RolePersonal rop where rop.idPersonal=:id")
//    RolePersonal findPersonal(@Param("id") int id);
//
//    @Query("select  p.idRole from RolePersonal p where p.idPersonal=:id")
//    int getRolePersonal(@Param("id") int id);
}
