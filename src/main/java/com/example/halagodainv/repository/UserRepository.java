package com.example.halagodainv.repository;

import com.example.halagodainv.dto.user.UserDto;
import com.example.halagodainv.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUserName(String userName);

    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByEmailOrUserName(String email,String userName);

    @Query("select new com.example.halagodainv.dto.user.UserDto(u.id,u.email,u.userName,r.name) from UserEntity u left join RoleEntity r " +
            "on r.idRole = u.role where u.id =:userId ")
    UserDto getUser(@Param("userId") int userId);


}
