package com.example.halagodainv.repository;
import com.example.halagodainv.model.Influencer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface InfluencerRepository extends JpaRepository<Influencer,Integer> {
    @Query("Select influ from Influencer influ where influ.id=:id")
    Influencer findInfluencer(@Param("id")int id);

    @Modifying
    @Query("delete from  Influencer influ where influ.id=?1")
    void deleteID(@Param("id")int id);
}
