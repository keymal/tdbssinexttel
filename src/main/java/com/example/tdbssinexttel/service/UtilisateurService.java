package com.example.tdbssinexttel.service;

import com.example.tdbssinexttel.model.Role;
import com.example.tdbssinexttel.model.Utilisateur;

import java.util.List;

public interface UtilisateurService {
    List<Utilisateur> listUtilisateur();

    List<Role> listRoles();

    void saveUser(Utilisateur utilisateur);
}
