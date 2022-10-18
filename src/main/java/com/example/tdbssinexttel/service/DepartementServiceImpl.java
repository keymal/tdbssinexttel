package com.example.tdbssinexttel.service;

import com.example.tdbssinexttel.exception.DepartementNotFoundException;
import com.example.tdbssinexttel.model.Departement;
import com.example.tdbssinexttel.repository.DepartementRepository;
import com.example.tdbssinexttel.utils.enums.Etat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class DepartementServiceImpl implements DepartementService {

    @Autowired
    DepartementRepository departementRepository;
    @Override
    public Departement save(Departement departement) {
        return null;
    }


    @Override
    public List<Departement> listeDepartement(){

        return departementRepository.findDepartementsByEtat(Etat.ACTIF);

    }

    @Override
    public void updateUserEnabledStatus(Integer id, boolean status) {


        departementRepository.updateEnableStatus(id, status);

    }


    @Override
    public Departement getById(Integer id) throws DepartementNotFoundException {
        try {
            return departementRepository.findById(id).get();

        } catch (
                NoSuchElementException ex) {
            throw new DepartementNotFoundException("Impossible de trouver :" + id);
        }
    }

    @Override
    public void delete(Integer id) throws DepartementNotFoundException {

        Long countById = departementRepository.countById(id);

        if (countById == null || countById == 0) {

            throw new DepartementNotFoundException("Impossible de trouver :" + id);

        }
        Departement departement = departementRepository.findById(id).get();

        departement.setEtat(Etat.INACTIF);

        departementRepository.save(departement);
    }
}
