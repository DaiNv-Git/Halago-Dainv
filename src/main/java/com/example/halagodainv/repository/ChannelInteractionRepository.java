package com.example.halagodainv.repository;
import com.example.halagodainv.model.ChannelInteractionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelInteractionRepository extends JpaRepository<ChannelInteractionEntity,String> {

}
