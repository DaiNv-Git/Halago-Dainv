package com.example.halagodainv.repository;
import com.example.halagodainv.model.Footer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FooterRepository extends JpaRepository<Footer,String> {

//    @Query("Select foot from Footer foot ")
//    List<Footer> getListFooter();
//
//    @Query("Select foot from Footer foot where  foot.id=:id")
//    Footer findFooterById(@Param("id")int id);
}
