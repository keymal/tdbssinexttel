package com.example.tdbssinexttel.service;

import com.example.tdbssinexttel.exception.DirectionNotFoundException;
import com.example.tdbssinexttel.model.Direction;
import com.example.tdbssinexttel.repository.DirectionRepository;
import com.example.tdbssinexttel.utils.enums.Etat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DirectionServiceImpl implements DirectionService {
    @Autowired
    DirectionRepository directionRepository;
    @Override
    public Direction save(Direction direction) {

        boolean isUserUpdate = (direction.getId() != null);
        if (isUserUpdate) {

            Direction existingUser = directionRepository.findById(direction.getId()).get();

            existingUser.setLibellé(direction.getLibellé());

            return directionRepository.save(existingUser);

        } else {
            direction.setEtat(Etat.ACTIF);
            direction.setStatus(true);
            return directionRepository.save(direction);
        }
    }

    @Override
    public List<Direction> list() {
        return directionRepository.findDirectionsByEtat(Etat.ACTIF);
    }

    @Override
    public void updateUserEnabledStatus(Integer id, boolean status) {


        directionRepository.updateEnableStatus(id, status);

    }


    @Override
    public void delete(Integer id) throws DirectionNotFoundException {

        Long countById = directionRepository.countById(id);

        if (countById == null || countById == 0) {

            throw new DirectionNotFoundException("Impossible de trouver :" + id);

        }
        Direction direction = directionRepository.findById(id).get();

        direction.setEtat(Etat.INACTIF);

        directionRepository.save(direction);
    }

    @Override
    public Boolean checkDirectionByNm(Integer id, String email) {
        Direction direction = directionRepository.findDirectionByNomIgnoreCaseAndEtat(email,Etat.ACTIF);


        if (direction == null) return true;
        boolean isCreatingnew = (id == null);
        if (isCreatingnew) {
            if (direction != null) return false;
        } else {
            if (direction.getId() != id) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Direction getById(Integer id){
        return directionRepository.findById(id).get();
    }
}
