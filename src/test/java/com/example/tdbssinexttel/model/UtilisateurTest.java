package com.example.tdbssinexttel.model;

import static org.junit.jupiter.api.Assertions.*;

import com.example.tdbssinexttel.repository.RoleRepository;
import com.example.tdbssinexttel.repository.UtilisateurRepository;
import com.example.tdbssinexttel.utils.enums.EtatUtilisateur;
import com.example.tdbssinexttel.utils.enums.ListeDesRoles;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class UtilisateurTest {


    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    @Autowired
    TestEntityManager testEntityManager;

    @Test
    public void testCreateUser() {

        Role role = testEntityManager.find(Role.class, 1);
        Utilisateur utilisateur = new Utilisateur("amougouandre37@gmail.com", passwordEncoder.encode("master"), true, "", "", "", EtatUtilisateur.ACTIF, new ArrayList<>());
        utilisateur.addRole(role);

        Utilisateur save = utilisateurRepository.save(utilisateur);
        assertThat(save.getId()).isGreaterThan(0);


    }

    @Test
    public void testListUsers() {

        Iterable<Utilisateur> listUsers = utilisateurRepository.findAll();

        listUsers.forEach(utilisateur -> System.out.println(utilisateur));
    }

    @Test
    public void findById() {
        Utilisateur utilisateur = utilisateurRepository.findById(1).get();

        assertThat(utilisateur).isNotNull();

    }

    @Test
    public void updateUser() {
        Utilisateur utilisateur = utilisateurRepository.findById(1).get();

        utilisateur.setStatus(true);
        utilisateur.setPassword("master");
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));

        Role role = testEntityManager.find(Role.class, 5);

        utilisateur.getRoles().add(role);

    }

}
