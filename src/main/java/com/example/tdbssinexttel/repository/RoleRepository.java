package com.example.tdbssinexttel.repository;

import com.example.tdbssinexttel.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findRoleByNom(String nom);
}
