package com.example.halagodainv.repository;

import com.example.halagodainv.dto.kol.KolCelMapEntity;
import com.example.halagodainv.model.BookKolsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookKolRepository extends JpaRepository<BookKolsEntity, Long> {

    @Query("select new com.example.halagodainv.dto.kol.KolCelMapEntity(b.id,b.title,bl.titleEN,b.imageKol1,b.imageKol2,b.approach,b.interact,b.ratioInteract,b.introduce,b.introduceDetail,bl.introduceEN,bl.introduceDetailEN) from BookKolsEntity b left join " +
            "BookKolsLanguageEntity bl on bl.kolIdd = b.id where b.id =1 ")
    KolCelMapEntity getAll();
}
