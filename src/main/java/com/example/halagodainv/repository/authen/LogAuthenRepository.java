package com.example.halagodainv.repository.authen;

import com.example.halagodainv.model.LogAuthenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;


@Repository
public interface LogAuthenRepository extends JpaRepository<LogAuthenEntity, Long> {

    Optional<LogAuthenEntity> findByUserId(int userId);
}
