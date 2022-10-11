package com.example.tdbssinexttel.service;

import com.example.tdbssinexttel.exception.UserNotFoundException;
import com.example.tdbssinexttel.model.Role;
import com.example.tdbssinexttel.model.Utilisateur;
import com.example.tdbssinexttel.repository.RoleRepository;
import com.example.tdbssinexttel.repository.UtilisateurRepository;
import com.example.tdbssinexttel.utils.enums.EtatUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Utilisateur> listUtilisateur() {
        return utilisateurRepository.findUtilisateursByEtatUtilisateur(EtatUtilisateur.ACTIF);
    }

    @Override
    public List<Role> listRoles() {

        return roleRepository.findAll();

    }

    @Override
    public void saveUser(Utilisateur utilisateur) {
        boolean isUserUpdate = (utilisateur.getId() !=null);
                if(isUserUpdate){
                    utilisateurRepository.save(utilisateur);

                }else {
                    utilisateur.setEtatUtilisateur(EtatUtilisateur.ACTIF);
                }
    }


    @Override

    public Boolean findUserByEmail(Integer id, String email) {
        Utilisateur userEmail = utilisateurRepository.findUtilisateurByEmailIgnoreCase(email);

        if (userEmail == null) return true;
        boolean isCreatingnew = (id == null);
        if (isCreatingnew) {
            if (userEmail != null) return false;
        } else {
            if (userEmail.getId() != id) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Utilisateur get(Integer id) throws UserNotFoundException {
        try {

            return utilisateurRepository.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new UserNotFoundException("Impossible de trouver :" + id);
        }
    }

    @Override
    public void delete(Integer id) throws UserNotFoundException {
        Long countById = utilisateurRepository.countById(id);
        if (countById == null || countById == 0) {
            throw new UserNotFoundException("Impossible de trouver :" + id);
        }
        Utilisateur utilisateur = utilisateurRepository.findById(id).get();
        utilisateur.setEtatUtilisateur(EtatUtilisateur.INACTIF);
        utilisateurRepository.save(utilisateur);
    }

}
