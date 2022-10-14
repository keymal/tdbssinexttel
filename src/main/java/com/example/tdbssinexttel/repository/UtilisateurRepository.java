package com.example.tdbssinexttel.repository;

import com.example.tdbssinexttel.model.Utilisateur;
import com.example.tdbssinexttel.utils.enums.EtatUtilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Utilisateur findUtilisateurByEmailIgnoreCase(String email);

    public long countById(Integer id);

    List<Utilisateur> findUtilisateursByEtatUtilisateur(EtatUtilisateur etatUtilisateur);

    @Query("update  Utilisateur u set u.status  = ?2 where  u.id = ?1")
    @Modifying
    public void updateEnableStatus(Integer id , boolean enabled);

    Utilisateur findByEmail(String email);
}
