package com.example.tdbssinexttel.service;

import com.example.tdbssinexttel.model.Utilisateur;
import com.example.tdbssinexttel.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public List<Utilisateur> listUtilisateur(){
        return  utilisateurRepository.findAll();
    }

}
