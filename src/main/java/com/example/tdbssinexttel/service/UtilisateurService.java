package com.example.tdbssinexttel.service;

import com.example.tdbssinexttel.exception.UserNotFoundException;
import com.example.tdbssinexttel.model.Role;
import com.example.tdbssinexttel.model.Utilisateur;

import java.util.List;

public interface UtilisateurService {
    List<Utilisateur> listUtilisateur();

    List<Role> listRoles();

    void saveUser(Utilisateur utilisateur);

    Boolean findUserByEmail(Integer id, String email);

    Utilisateur get(Integer id) throws UserNotFoundException;

    void delete(Integer id) throws UserNotFoundException;

    void  updateUserEnabledStatus(Integer id, boolean status);
}
