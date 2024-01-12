package com.example.halagodainv.repository;

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

    Optional<UserEntity> findByEmailOrUserName(String email, String userName);

    @Query(value = "select * from users u inner join role_user r " +
            "on r.id_role = u.role_id where u.id =:userId " ,nativeQuery = true)
    UserEntity getUser(@Param("userId") int userId);

    @Query(value = "select * from users u inner join role_user r " +
            "on r.id_role = u.role_id where u.username like concat('%',:userName,'%') order by u.id desc ",nativeQuery = true)
    List<UserEntity> getAll(@Param("userName") String userName, Pageable pageable);

    @Query(value = "select count(*) from users u inner join role_user r " +
            "on r.id_role = u.role_id where u.username like concat('%',:userName,'%') ",nativeQuery = true)
    int totalElementAll(@Param("userName") String userName);
}
