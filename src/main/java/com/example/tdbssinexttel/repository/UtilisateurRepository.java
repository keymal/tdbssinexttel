package com.example.tdbssinexttel.repository;

import com.example.tdbssinexttel.model.Utilisateur;
import com.example.tdbssinexttel.utils.enums.EtatUtilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Utilisateur findUtilisateurByEmailIgnoreCase(String email);

    public long countById(Integer id);

    List<Utilisateur> findUtilisateursByEtatUtilisateur(EtatUtilisateur etatUtilisateur);
}
