package com.example.halagodainv.repository;
import com.example.halagodainv.model.BookKolsLanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BookKolsLanguageRepository extends JpaRepository<BookKolsLanguageEntity,Long> {

}
