package com.example.halagodainv.repository;

import com.example.halagodainv.dto.foot.FootDto;
import com.example.halagodainv.model.FooterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FooterRepository extends JpaRepository<FooterEntity, Integer> {

    @Query("Select new com.example.halagodainv.dto.foot.FootDto(f.id,f.company,fl.company,f.address,fl.address,f.email,f.hotline,f.facebook,f.zalo,f.youtube,f.tiktok,f.instagram,fl.languageKey) " +
            "from FooterEntity f left join FooterLanguageEntity fl on f.id=fl.idFooter")
    FootDto getFooter();

    @Query("Select new com.example.halagodainv.dto.foot.FootDto(f.id,f.company,fl.company,f.address,fl.address,f.email,f.hotline,f.facebook,f.zalo,f.youtube,f.tiktok,f.instagram,fl.languageKey) " +
            "from FooterEntity f left join FooterLanguageEntity fl on f.id=fl.idFooter and f.id =:id")
    FootDto getFooter(@Param("id") int id);
}
