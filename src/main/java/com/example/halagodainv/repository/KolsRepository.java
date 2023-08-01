package com.example.halagodainv.repository;


import com.example.halagodainv.model.BookKolsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KolsRepository extends JpaRepository<BookKolsEntity,String> {


}
