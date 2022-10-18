package com.example.tdbssinexttel.service;

import com.example.tdbssinexttel.exception.DepartementNotFoundException;
import com.example.tdbssinexttel.model.Departement;
import com.example.tdbssinexttel.model.Direction;
import com.example.tdbssinexttel.model.Utilisateur;

import java.util.List;

public interface DepartementService {
    Departement save(Departement departement);

    List<Departement> listeDepartement();

    void updateUserEnabledStatus(Integer id, boolean status);

    Departement getById(Integer id) throws DepartementNotFoundException;

    void delete(Integer id) throws DepartementNotFoundException;
}
