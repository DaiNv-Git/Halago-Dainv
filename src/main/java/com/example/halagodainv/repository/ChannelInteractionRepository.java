package com.example.halagodainv.repository;
import com.example.halagodainv.model.ChannelInteraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelInteractionRepository extends JpaRepository<ChannelInteraction,String> {

}
