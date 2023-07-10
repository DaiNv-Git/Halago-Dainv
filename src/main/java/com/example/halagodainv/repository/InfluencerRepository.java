package com.example.halagodainv.repository;



import com.example.halagodainv.model.Influencer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfluencerRepository extends JpaRepository<Influencer,String> {
//    @Query("Select COUNT(influ) from Influencer influ where  (:name IS NULL OR lower(influ.name) LIKE lower(concat('%', concat(:name, '%')))) and " +
//            "(-1 in :status or influ.status IN :status)")
//    int getListInfluencer(@Param("name") String name, @Param("status") int status);
//
//    @Query("Select influ from Influencer influ where influ.id=:id")
//    Influencer findInfluencer(@Param("id")int id);
//
//    @Query("select u from Influencer u where u.email=:email")
//    Influencer checkMailInfluencer(@Param("email")String email);
//
//    @Query("Select influ from Influencer influ where influ.fbId=:id")
//    Influencer findInfluencerByFbId (@Param("id")String id);
//
//    @Query("Select influ from Influencer influ where lower(influ.name ) like lower(concat('%', concat(:name, '%')))")
//    List<Influencer> findInfluencerByName(@Param("name")String name);
//
//    @Query(value = "Select COUNT(*) from influencer where  (-1 in (:industryId) or industry_id IN (:industryId)) " +
//            "AND TIMESTAMPDIFF (YEAR, birthday, CURDATE()) >= :fromAge and TIMESTAMPDIFF (YEAR, birthday, CURDATE()) <= :toAge " +
//            "AND (-1 in (:sex) or sex IN (:sex)) " +
//            "AND (-1 in (:cityId) or city IN (:cityId)) ",nativeQuery = true)
//    int countInfluencer(@Param("industryId") int industryId,
//                        @Param("fromAge") int fromAge,
//                        @Param("toAge") int toAge,
//                        @Param("sex") int sex,
//                        @Param("cityId") int cityId);
}
