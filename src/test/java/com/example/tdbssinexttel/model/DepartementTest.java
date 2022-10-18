package com.example.tdbssinexttel.model;

import com.example.tdbssinexttel.repository.DepartementRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DepartementTest {

    @Autowired
    DepartementRepository departementRepository;

    @Test
    public void createDepartement(){
        Departement departement = new Departement("RESEAU");
        departementRepository.save(departement);
        System.err.println(departement);
    }



}
