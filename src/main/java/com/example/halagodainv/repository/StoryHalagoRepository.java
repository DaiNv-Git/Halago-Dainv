package com.example.halagodainv.repository;

import com.example.halagodainv.model.StoryHalagoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StoryHalagoRepository extends JpaRepository<StoryHalagoEntity,Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM StoryHalagoEntity e WHERE e.id IN :ids")
    void deleteByIds(@Param("ids") List<Long> ids);
}
