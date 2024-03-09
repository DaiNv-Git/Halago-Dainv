package com.example.halagodainv.repository.authen;

import com.example.halagodainv.model.authen.AuthenPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface AuthenPasswordRepository extends JpaRepository<AuthenPassword, Long> {

    Optional<AuthenPassword> findByEmail(String email);

    Optional<AuthenPassword> findByCodeAndEmail(String code,String email);

    @Transactional
    @Modifying
    void deleteByEmail(String email);
}
