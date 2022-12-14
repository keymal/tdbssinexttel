package com.example.tdbssinexttel.service;

import com.example.tdbssinexttel.exception.UserNotFoundException;
import com.example.tdbssinexttel.model.Role;
import com.example.tdbssinexttel.model.Utilisateur;
import com.example.tdbssinexttel.repository.RoleRepository;
import com.example.tdbssinexttel.repository.UtilisateurRepository;
import com.example.tdbssinexttel.utils.enums.Etat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<Utilisateur> listUtilisateur() {
        return utilisateurRepository.findUtilisateursByEtatUtilisateurAndIdNot(Etat.ACTIF, 1);
    }

    @Override
    public List<Role> listRoles() {

        return roleRepository.findAll();

    }

    @Override
    public Utilisateur saveUser(Utilisateur utilisateur) {
        boolean isUserUpdate = (utilisateur.getId() != null);
        if (isUserUpdate) {

            Utilisateur existingUser = utilisateurRepository.findById(utilisateur.getId()).get();

            existingUser.setRoles(utilisateur.getRoles());
            existingUser.setStatus(utilisateur.getStatus());

            return utilisateurRepository.save(existingUser);

        } else {
            utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
            utilisateur.setEtatUtilisateur(Etat.ACTIF);
            return utilisateurRepository.save(utilisateur);
        }
    }


    @Override

    public Boolean findUserByEmail(Integer id, String email) {
        Utilisateur userEmail = utilisateurRepository.findByEmail(email);
        System.err.println(userEmail);

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

        utilisateur.setEtatUtilisateur(Etat.INACTIF);

        utilisateurRepository.save(utilisateur);
    }


    @Override
    public void updateUserEnabledStatus(Integer id, boolean status) {


        utilisateurRepository.updateEnableStatus(id, status);

    }


    @Override
    public Utilisateur getUserByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }


    @Override
    public Utilisateur updateAccount(Utilisateur utilisateur) {
        Utilisateur existUser = utilisateurRepository.findById(utilisateur.getId()).get();
        if (!utilisateur.getPassword().isEmpty()) {
            existUser.setPassword(passwordEncoder.encode(utilisateur.getPassword()));

        }
        if (utilisateur.getPhotos() != null) {
            existUser.setPhotos(utilisateur.getPhotos());
        }
        return utilisateurRepository.save(existUser);


    }

    @Override
    public Utilisateur getById(Integer id ){
        return utilisateurRepository.findById(id).get();
    }
}
