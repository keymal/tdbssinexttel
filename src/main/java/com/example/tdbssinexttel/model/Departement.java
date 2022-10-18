package com.example.tdbssinexttel.model;

import com.example.tdbssinexttel.utils.enums.Etat;
import com.example.tdbssinexttel.utils.enums.EtatDepartement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Departement  extends AbstractEntity implements Serializable {
    @Column(unique = true)
    private String nom;

    private String description;

    @ManyToOne
    private Utilisateur utilisateur;

    @ManyToOne
    private Direction direction;

    private boolean status;

    @Enumerated(EnumType.STRING)
    private Etat etat;


    public Departement(String nom) {
        this.nom = nom;
    }









}
