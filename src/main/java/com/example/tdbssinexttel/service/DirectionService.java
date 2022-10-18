package com.example.tdbssinexttel.service;

import com.example.tdbssinexttel.exception.DirectionNotFoundException;
import com.example.tdbssinexttel.model.Departement;
import com.example.tdbssinexttel.model.Direction;

import java.util.List;

public interface DirectionService {

    Direction save(Direction direction);

    List<Direction> list();


    void updateUserEnabledStatus(Integer id, boolean status);

    void delete(Integer id) throws DirectionNotFoundException;

    Boolean checkDirectionByNm(Integer id, String email);

    Direction getById(Integer id);
}
