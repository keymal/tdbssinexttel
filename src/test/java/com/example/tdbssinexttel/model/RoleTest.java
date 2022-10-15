package com.example.tdbssinexttel.model;

import com.example.tdbssinexttel.repository.RoleRepository;
import com.example.tdbssinexttel.utils.enums.ListeDesRoles;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class RoleTest {

    @Autowired
    RoleRepository roleRepository;

    @Test
    public void test() {
        ListeDesRoles[] values = ListeDesRoles.values();
        for (ListeDesRoles listeDesRoles : values
        ) {
            Role role = new Role(listeDesRoles.name(), "");

            roleRepository.save(role);
        }

    }

}
