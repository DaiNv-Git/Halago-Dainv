package com.example.halagodainv.repository;

import com.example.halagodainv.dto.user.UserDto;
import com.example.halagodainv.model.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUserName(String userName);

    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByEmailOrUserName(String email,String userName);

    @Query("select new com.example.halagodainv.dto.user.UserDto(u.id,u.email,u.userName,r.name) from UserEntity u left join RoleEntity r " +
            "on r.idRole = u.role where u.id =:userId ")
    UserDto getUser(@Param("userId") int userId);

    @Query("select new com.example.halagodainv.dto.user.UserDto(u.id,u.email,u.userName,r.name) from UserEntity u left join RoleEntity r " +
            "on r.idRole = u.role where u.userName like concat('%',:userName,'%') ")
    List<UserDto> getAll(@Param("userName") String userName, Pageable pageable);
    @Query("select count(u) from UserEntity u left join RoleEntity r " +
            "on r.idRole = u.role where u.userName like concat('%',:userName,'%')")
    int  totalElementAll(@Param("userName") String userName);


}
