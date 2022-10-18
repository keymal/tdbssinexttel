package com.example.tdbssinexttel.repository;

import com.example.tdbssinexttel.model.Departement;
import com.example.tdbssinexttel.model.Direction;
import com.example.tdbssinexttel.utils.enums.Etat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartementRepository extends JpaRepository<Departement, Integer> {

    List<Departement> findDepartementsByEtat(Etat etatUtilisateur);

    @Query("update  Departement u set u.status  = ?2 where  u.id = ?1")
    @Modifying
    public void updateEnableStatus(Integer id , boolean enabled);


    public long countById(Integer id);

}
