package com.example.halagodainv.repository.viewdisplay.managekol;

import com.example.halagodainv.model.viewdisplayentity.managekol.ManageKolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManageKolRepository extends JpaRepository<ManageKolEntity, Integer> {
}
