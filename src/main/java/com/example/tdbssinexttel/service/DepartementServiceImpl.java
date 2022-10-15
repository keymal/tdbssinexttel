package com.example.tdbssinexttel.service;

import com.example.tdbssinexttel.model.Departement;
import com.example.tdbssinexttel.repository.DepartementRepository;
import com.example.tdbssinexttel.utils.enums.Etat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DepartementServiceImpl implements DepartementService {

    @Autowired
    DepartementRepository departementRepository;

    @Override
    public Departement save(Departement departement) {
        boolean isUserUpdate = (departement.getId() != null);
        if (isUserUpdate) {

            Departement exist = departementRepository.findById(departement.getId()).get();




            return    departementRepository.save(departement);

        }

        else {
            departement.setEtat(Etat.ACTIF);
            return departementRepository.save(departement);
        }    }
}
