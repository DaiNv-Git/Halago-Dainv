package com.example.halagodainv.repository;
import com.example.halagodainv.model.IntroduceLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IntroduceLanguageRepository extends JpaRepository<IntroduceLanguage,String> {
//    @Query("Select lang from IntroduceLanguage lang where lang.idIntroduce=:id and lang.languageKey=:language")
//    IntroduceLanguage findIntroLanguage(@Param("id")int id,
//                                        @Param("language")String language);
}
