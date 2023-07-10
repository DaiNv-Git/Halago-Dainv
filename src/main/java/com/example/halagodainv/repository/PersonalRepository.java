package com.example.halagodainv.repository;
import com.example.halagodainv.model.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, String> {
//    @Query("select p from Personal  p where p.idPersonal=:id")
//    Personal findPersonal(@Param("id") int id);
//
//    @Query("select p from Personal  p where p.idRole=2 or p.idRole=6")
//    Personal findRole();
//
//    @Query("select p from Personal  p where  p.idRole=6 and (p.status=0  or p.status =1)")
//    List<Personal> findSale();
//
//    @Query("SELECT p FROM Personal p WHERE p.status=1 OR p.status=0 order by p.idPersonal asc ")
//    List<Personal> findAll();
//
//    @Modifying(clearAutomatically = true)
//    @Query("UPDATE Personal p set p.fullName=:full_name where p.idPersonal=:id")
//    void updateName(@Param("id") int id, @Param("full_name") String full_name);
//
//    @Query("select p.password from Personal p where p.phone=:phone  and (p.status=1 OR p.status=0) ")
//    String findPassword(@Param("phone") String phone);
//
//
//    @Modifying
//    @Query("UPDATE Personal p set p.gender=:gender where p.idPersonal=:id")
//    void updateGender(@Param("id") int id, @Param("gender") String gender);
//
//    @Modifying(clearAutomatically = true)
//    @Query("UPDATE Personal p set p.cmnd=:cmnd where p.idPersonal=:id  ")
//    void updateCard(@Param("id") int id, @Param("cmnd") String cmnd);
//
//    @Modifying(clearAutomatically = true)
//    @Query("UPDATE Personal p set p.bankAccount=:bank_account,p.bankName=:bank_name,p.bankBranch=:bank_branch where p.idPersonal=:id")
//    void updateAcountBank(@Param("id") int id, @Param("bank_account") String bank_account, @Param("bank_name") String bank_name, @Param("bank_branch") String bank_branch);
//
//    @Modifying(clearAutomatically = true)
//    @Query("UPDATE Personal p set p.avatar=:avatar where p.idPersonal=:id")
//    void updateAvatar(@Param("id") int id, @Param("avatar") String avatar);
//
//    @Query("select p from Personal p where (p.phone=:user or p.email=:user) and (p.status =1 or p.status=0)")
//    Personal findByPhoneOrEmail(@Param("user") String user);
//
//    @Query("select p from Personal p where p.phone=:phone and (p.status =1 or p.status=0)")
//    Personal findByPhone(@Param("phone") String phone);
//
//    @Query("select p from Personal p where p.cmnd=:cmnd and p.status =1")
//    Personal findByCmnd(@Param("cmnd") String cmnd);
//
//    @Query("select p from Personal p where p.introduceCodeUser=:introduceCodeUser and p.status=1")
//    Personal findCode(@Param("introduceCodeUser") String code);
//
//    @Query("select p from Personal p where (p.email=:email or p.phone=:email ) and (p.status =1 or p.status=0)")
//    Personal findEmail(@Param("email") String email);
//
//
//
//    @Query("select p from Personal p where p.bankAccount=:bankAccount and p.status =1")
//    Personal findByBankAccount(@Param("bankAccount") String bankAccount);
//
//    Personal findByEmail(@Param("email") String email);
//
//    @Query("select p from Personal p where p.phone=:phone  and (p.status =1 or p.status=0)")
//    Personal login(@Param("phone") String phone);
//
//    @Query("select p from Personal p where p.phone=:phone and p.password=:password and  p.status =1")
//    PersonalLoginModel mglogin(@Param("phone") String phone, @Param("password") String password);
//
//    //    @Query("select p.idPersonal from Personal p where p.email=:email and p.phone=:phone")
//    Personal findByPhoneAndEmail(@Param("email") String email, @Param("phone") String phone);
//
//    @Modifying
//    @Transactional
//    @Query("UPDATE Personal p set p.password=:new_pass where p.idPersonal=:id")
//    void updatePassword(@Param("id") int id, @Param("new_pass") String new_pass);
//
//
//    @Modifying(clearAutomatically = true)
//    @Query("UPDATE Personal p set p.fullName=:fullName,p.avatar=:avatar,p.birthDay=:birthDay,p.gender=:gender,p.address=:address,p.email=:email,p.phone=:phone,p.cmnd=:cmnd,p.bankAccount=:bankAccount,p.bankBranch=:bankBranch,p.bankName=:bankName,p.imageCmnd=:imageCmnd where p.idPersonal=:id")
//    void changeInfoPersonal(@Param("id") int id, @Param("fullName") String fullName, @Param("avatar") String avatar, @Param("birthDay") String birthDay, @Param("gender") String gender, @Param("address") String address, @Param("email") String email, @Param("phone") String phone, @Param("cmnd") String cmnd, @Param("bankAccount") String bankAccount, @Param("bankBranch") String bankBranch, @Param("bankName") String bankName,@Param("imageCmnd")String imageCmnd);
//
//    @Query("select p.password from Personal p where p.idPersonal=:id")
//    String findPass(@Param("id") int id);
//
//    @Query("select p from Personal p where p.referenceId=:id")
//    Personal findReferenceId(@Param("id") int id);
//
//    @Query("select p.password from Personal p where (p.phone=:phone or p.email=:phone) and (p.status=1 or p.status=0)")
//    String findPasswordPersonal(@Param("phone") String phone);
//
//    //
////    @Query("select hs.score from HistoryScore hs where hs.idPersonal=:id")
////    String findScore(@Param("id") int id);
//
////    @Query("SELECT rp.score from RankPersonal rp where rp.idPersonal=:id")
////    String findScoreRank(@Param("id") int id);
//
////    @Query("select r.name from RankPersonal rp,Rank r where r.idRank=rp.idRank and rp.idPersonal=:id")
////    String findNameRank(@Param("id") int id);
//
////    @Query("select distinct p.name from SellingHistory sh,  Project p , Apartment a , Building b, Sector se  where (sh.status=1 or sh.status = 0) and sh.idApartment = a.idApartment  and  a.idBuilding = b.idBuilding and b.idSector = se.idSector and se.idProject = p.idProject and sh.idPersonal=:id")
////    List<String> findNameProject(@Param("id") int id);
//
//    @Query("select p from Personal p where p.introduceCodeUser=:introduceCode")
//    Personal findIntroduceCode(@Param("introduceCode") String introduceCode);
//
//    @Query("select p.idPersonal from Personal p where p.introduceCodeUser=:introduceCode")
//    int findIntroduce(@Param("introduceCode") String introduceCode);
//
//    @Modifying
//    @Query("update Personal p set p.referenceId=:referenceID where p.idPersonal=:id")
//    void updateReferenceId(@Param("id") int id, @Param("referenceID") int referenceID);
//
//    @Modifying
//    @Query("update Personal p set p.active=1 where p.idPersonal=:idPersonal")
//    void updateActive(@Param("idPersonal") int idPersonal);
//
//    //    @Modifying
////    @Query("update Facebook p set p.fbID=:fbID where p.idPersonal=:idPersonal")
////    void updatefbId(@Param("fbID")String fbID,@Param("idPersonal")int idPersonal);
//    Personal findByFbID(@Param("fbID") String fbID);
//
//    @Modifying
//    @Query("update Personal p set p.fbID=:fbID where p.idPersonal=:idPersonal")
//    void updateFbID(@Param("fbID") String fbID, @Param("idPersonal") int idPersonal);
//
//    @Modifying
//    @Query("update Personal p set p.fbID=:fbID where p.idPersonal=:idPersonal")
//    void updatefbIDByEmail(@Param("fbID") String fbID, @Param("idPersonal") int idPersonal);
//
//    @Modifying
//    @Transactional
//    @Query("update Personal p set p.fbID=:fbID,p.avatar=:photo,p.fullName=:name where p.idPersonal=:idPersonal")
//    void updatePersonalByFbId(@Param("fbID") String fbID, @Param("photo") String photo, @Param("name") String name, @Param("idPersonal") int idPersonal);
//
//    @Transactional
//    @Modifying(clearAutomatically = true)
//    @Query("UPDATE Personal p set p.phone=:phone where p.idPersonal=:id")
//    void updatePhone(@Param("id") int id, @Param("phone") String phone);
//
//    @Transactional
//    @Modifying
//    @Query("UPDATE Personal p set p.active=0 where p.idPersonal=:idPersonal")
//    void updateActiveOff(@Param("idPersonal") int idPersonal);
//
//    @Transactional
//    @Modifying
//    @Query("update Personal p set p.groupProject=:idProject where p.idPersonal=:idPersonal")
//    void updateProject(@Param("idProject") String idProject, @Param("idPersonal") int idPersonal);
//
//    @Query("select p.groupProject from Personal p where p.idPersonal=:idPersonal")
//    String findProject(@Param("idPersonal") int idPersonal);
}
