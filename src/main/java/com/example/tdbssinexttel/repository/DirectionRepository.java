package com.example.tdbssinexttel.repository;

import com.example.tdbssinexttel.model.Direction;
import com.example.tdbssinexttel.model.Utilisateur;
import com.example.tdbssinexttel.utils.enums.Etat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DirectionRepository extends JpaRepository<Direction, Integer> {

    List<Direction> findDirectionsByEtat(Etat etatUtilisateur);


    @Query("update  Direction u set u.status  = ?2 where  u.id = ?1")
    @Modifying
    public void updateEnableStatus(Integer id , boolean enabled);


    Direction findDirectionByNomIgnoreCaseAndEtat(String nom, Etat etat);


    public long countById(Integer id);

}
