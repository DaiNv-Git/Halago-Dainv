package com.example.halagodainv.repository;


import com.example.halagodainv.model.RoleProject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleProjectRepository extends JpaRepository<RoleProject, String> {
//    @Query("select rp from RoleProject rp where rp.idRoleProject=:id")
//    RoleProject findRoleProject(@Param("id") int id);
//
//    @Query("select  rp from RoleProject  rp ")
//    List<RoleProject> findAll();
}
