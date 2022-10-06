package com.example.tdbssinexttel.repository;

import com.example.tdbssinexttel.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Utilisateur findUtilisateurByEmailIgnoreCase(String email);
}
