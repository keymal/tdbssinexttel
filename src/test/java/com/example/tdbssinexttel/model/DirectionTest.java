package com.example.tdbssinexttel.model;

import com.example.tdbssinexttel.repository.DirectionRepository;
import com.example.tdbssinexttel.utils.enums.Etat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DirectionTest {

    @Autowired
    DirectionRepository directionRepository;


    @Test
    public void creer(){
        Direction direction = new Direction("RESEAU");
        direction.setStatus(true);
        direction.setEtat(Etat.ACTIF);
        directionRepository.save(direction);

        Direction direction1 = new Direction("SYSTEME INFORMATION");
        direction1.setStatus(true);
        direction1.setEtat(Etat.ACTIF);
        directionRepository.save(direction1);


        assertThat(direction1).isNotNull();
        assertThat(direction).isNotNull();
    }
}
