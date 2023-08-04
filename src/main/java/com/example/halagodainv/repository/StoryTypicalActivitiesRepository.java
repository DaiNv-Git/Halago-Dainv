package com.example.halagodainv.repository;

import com.example.halagodainv.model.StoryTypicalActivitiesEntity;
import com.mysql.cj.log.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryTypicalActivitiesRepository extends JpaRepository<StoryTypicalActivitiesEntity, Long> {
}
