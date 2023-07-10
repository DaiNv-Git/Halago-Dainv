package com.example.halagodainv.repository;
import com.example.halagodainv.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
//    @Query("select u.password from User u where u.username=:username")
//    String findByUsername(@Param("username")String username);
//
//    @Query("select u from User u where (u.username=:username or u.email=:username)")
//    User findUser(@Param("username")String username);
//
//    @Query("select Count(u) from User u where u.email=:email")
//    int checkMail(@Param("email")String email);
//
//    @Query("select u from User u where u.username=:username")
//    User checkUsername(@Param("username")String username);
//
//    @Query("select u from User u where u.id=:id")
//    User findUserById(@Param("id")int id);

}
